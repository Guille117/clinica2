package portafolio.Clinica2.servicio.servicePaciente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import portafolio.Clinica2.dto.DtoPaciente;
import portafolio.Clinica2.dto.DtoPersona;
import portafolio.Clinica2.modelo.Paciente;
import portafolio.Clinica2.modelo.Persona;
import portafolio.Clinica2.repositorio.IPacienteRepository;

@Service
public class PacienteService implements IPacienteService{

    @Autowired
    private IPacienteRepository pr;

    @Override
    public void Sa(Paciente t) {
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
        if(dtoPac.getTelefono() != null){
            p.setTelefono(dtoPac.getTelefono());
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
