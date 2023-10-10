package portafolio.Clinica2.validacion.Medico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityNotFoundException;
import portafolio.Clinica2.modelo.Medico;
import portafolio.Clinica2.servicio.serviceEspecialidad.IEspecialidadService;

@Component
public class VEspecialidadMedico implements IValidarMedico{

    @Autowired
    private IEspecialidadService es;

    @Override
    public void validarMed(Medico med) {
        if(es.getOne(med.getEspecialidad().getIdEspecialidad()) == null){
            throw new EntityNotFoundException("Especialidad no encontrada");
        }
    }
    
}
