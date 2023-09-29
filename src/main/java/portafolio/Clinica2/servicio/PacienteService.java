package portafolio.Clinica2.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import portafolio.Clinica2.modelo.Paciente;
import portafolio.Clinica2.repositorio.IPacienteRepository;
import portafolio.Clinica2.repositorio.IPersonaResponRepository;
import portafolio.Clinica2.validacion.paciente.ValidarPaciente;

@Service
public class PacienteService implements IGenericService<Paciente>{

    @Autowired
    private IPacienteRepository pr;
    @Autowired
    private List<ValidarPaciente> val;
    @Autowired
    private IPersonaResponRepository responsable;

    @Override
    public void Sa(Paciente t) {
        val.forEach(v -> v.validar(t));
        if(t.getPersonaResponsable() != null){
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
    public void Up(Long id) {
        
    }

    @Override
    public void De(Long id) {
        pr.deleteById(id);
    }
    
}
