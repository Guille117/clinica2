package portafolio.Clinica2.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import portafolio.Clinica2.dto.DtoPaciente;
import portafolio.Clinica2.dto.DtoPersoResponsable;
import portafolio.Clinica2.dto.DtoPersona;
import portafolio.Clinica2.modelo.Paciente;
import portafolio.Clinica2.modelo.Persona;
import portafolio.Clinica2.modelo.PersonaResponsable;
import portafolio.Clinica2.repositorio.IPacienteRepository;
import portafolio.Clinica2.repositorio.IPersonaResponRepository;
import portafolio.Clinica2.validacion.ActualizarPaciente.ValidarActualizarPaciente;
import portafolio.Clinica2.validacion.paciente.ValidarPaciente;

@Service
public class PacienteService implements IPacienteService{

    @Autowired
    private IPacienteRepository pr;
    @Autowired
    private List<ValidarPaciente> val;
    @Autowired
    private IPersonaResponRepository responsable;
    @Autowired
    private PersoResponsableService SRespo;
    @Autowired
    private List<ValidarActualizarPaciente> valRes;

    @Override
    public void Sa(Paciente t) {
        val.forEach(v -> v.validar(t));
        if(t.getPersonaResponsable() != null && t.getPersonaResponsable().getIdResponsable() == null){
            responsable.save(t.getPersonaResponsable());
        }
        pr.save(t);
    }

    @Override
    public Paciente getOne(Long id) {
        return pr.findById(id).orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado"));
       
    }

    @Override
    public List<Paciente> getAll() {
        return pr.findAll();
    }

    @Override
    @Transactional
    public void Up(DtoPaciente dtoPac) {
        valRes.forEach(v -> v.ValidarPaciente(dtoPac));

        Paciente p = this.getOne(dtoPac.getIdPaciente());
        Persona paci = p.getP();
        DtoPersona DtoPaci = dtoPac.getP();

        if(DtoPaci != null){
            if(DtoPaci.getNombre() != null){
                paci.setNombre(DtoPaci.getNombre());
            }
            if(DtoPaci.getApellido() != null){
                paci.setApellido(DtoPaci.getApellido());
            }
            if(DtoPaci.getEdad() != null){
                paci.setEdad(DtoPaci.getEdad());
            }
        }
        if(dtoPac.getPersonaResponsable() != null && p.getPersonaResponsable() != null){
            DtoPersoResponsable DtoRes = dtoPac.getPersonaResponsable();
            DtoRes.setIdResponsable(p.getPersonaResponsable().getIdResponsable());
            SRespo.actualizarResponsable(DtoRes);
        }
        if(dtoPac.getDpi() != null){
            p.setDpi(dtoPac.getDpi());
        }
        if(dtoPac.getTelefono() != null){
            p.setTelefono(dtoPac.getTelefono());
        }
        if((!(pr.existsByIdPacienteAndPersonaResponsableIsNotNull(p.getIdPaciente()))) && dtoPac.getPersonaResponsable() != null){
            System.out.println(dtoPac.getPersonaResponsable().getP().getNombre());
            @Valid PersonaResponsable pRes = new PersonaResponsable(dtoPac.getPersonaResponsable());
            responsable.save(pRes);
            p.setPersonaResponsable(pRes);
        }
    }

    @Override
    public void De(Long id) {
        pr.deleteById(id);
    }

    @Override
    public List<Paciente> mayores() {
        return pr.findByPEdadGreaterThan(17);
    }

    @Override
    public List<Paciente> menores() {
        return pr.findByPEdadLessThan(18);
    }
    
}
