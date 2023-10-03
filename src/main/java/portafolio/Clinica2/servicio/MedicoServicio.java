package portafolio.Clinica2.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import portafolio.Clinica2.dto.DtoMedico;
import portafolio.Clinica2.dto.DtoPersona;
import portafolio.Clinica2.modelo.Especialidad;
import portafolio.Clinica2.modelo.Medico;
import portafolio.Clinica2.modelo.Persona;
import portafolio.Clinica2.repositorio.IMedicoRepository;
import portafolio.Clinica2.validacion.Medico.IValidarMedico;

@Service
public class MedicoServicio implements IMedicoService{

    @Autowired
    private IMedicoRepository mr;
    @Autowired
    private EspecialidadService er;
    @Autowired
    private List<IValidarMedico> validMed;

    @Override
    public void Sa(Medico t) {
        validMed.forEach(v -> v.validarMed(t));
        mr.save(t);
    }

    @Override
    public Medico getOne(Long id) {
        return mr.findById(id).orElseThrow(() -> new EntityNotFoundException("Médico no encontrado"));
    }

    @Override
    public List<Medico> getAll() {
       return mr.findAll(); 
    }

    @Override
    @Transactional
    public void Up(DtoMedico dtoMedico) {
       Medico med = this.getOne(dtoMedico.getIdMedico());
       Persona pMed = med.getP();
       DtoPersona pDto = dtoMedico.getP();

       if(pDto != null){
        if(pDto.getNombre() != null){
            pMed.setNombre(pDto.getNombre());
        }
        if(pDto.getApellido() != null){
            pMed.setApellido(pDto.getApellido());
        }
        if(pDto.getEdad() != null){
            pMed.setEdad(pDto.getEdad());
        }
       }
       if(dtoMedico.getDpi() != null){
        med.setDpi(dtoMedico.getDpi());
       }
       if(dtoMedico.getTelefono() != null){
        med.setTelefono(dtoMedico.getTelefono());
       }
       if(dtoMedico.getIdEspecialidad() != null){
        Especialidad esp = er.getOne(dtoMedico.getIdEspecialidad());
        med.setEspecialidad(esp);
       }

       validMed.forEach(v -> v.validarMed(med));
       med.setP(pMed);
       mr.save(med);
    }

    @Override
    public void De(Long id) {
       mr.deleteById(id);
    }

    @Override
    @Transactional
    public void activarMedico(Long id) {
       Medico med = this.getOne(id);
       med.setEstado(true);
       this.Sa(med);
    }

    @Override
    @Transactional
    public void desactivarMedico(Long id) {
        Medico med = this.getOne(id);
        med.setEstado(false);
        this.Sa(med);
    }

    @Override
    public List<Medico> activos(boolean estado) {
        return mr.findByEstado(estado);
    }

    @Override
    public List<Medico> medEspecialidad(String especialidad) {
        return mr.findByEspecialidadNombre(especialidad);
    }
}
