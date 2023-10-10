package portafolio.Clinica2.validacion.Medico;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import portafolio.Clinica2.modelo.Medico;
import portafolio.Clinica2.repositorio.IConsultaRepository;

@Component
public class VCambioEspecialidad implements IValidarMedico{

    @Autowired
    private IConsultaRepository cr;

    @Override
    public void validarMed(Medico med) {
        if(med.getEspecialidad() != null){
            if(cr.existsByMedicoIdMedicoAndFechaYHoraAfter(med.getIdMedico(), LocalDateTime.now())){
                throw new ValidationException("Se podrá modificar las cualidades del médico cuando no tenga consultas programadas");
            }
        }
    }
    
}
