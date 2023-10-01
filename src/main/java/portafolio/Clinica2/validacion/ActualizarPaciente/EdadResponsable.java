package portafolio.Clinica2.validacion.ActualizarPaciente;

import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import portafolio.Clinica2.dto.DtoPaciente;

@Component
public class EdadResponsable implements ValidarActualizarPaciente{

    @Override
    public void ValidarPaciente(DtoPaciente pacDto) {
        if(pacDto.getPersonaResponsable() != null){
            if(pacDto.getPersonaResponsable().getP() != null){
                if(pacDto.getPersonaResponsable().getP().getEdad() != null){
                    if(pacDto.getPersonaResponsable().getP().getEdad() < 18){
                        throw new ValidationException("La persona responsable del paciente debe ser mayor de edad.");
                    }
                }
            }
        }
    }
    
    // se valida que la persona responsable sea mayor de edad.
}
