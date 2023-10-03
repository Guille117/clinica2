package portafolio.Clinica2.validacion.paciente;

import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import portafolio.Clinica2.modelo.Paciente;

@Component
public class ValidarResponsable implements IValidarPaciente{

    @Override
    public void validar(Paciente pa) {
        if(pa.getPersonaResponsable() != null && pa.getPersonaResponsable().getIdResponsable() == null){
            if(pa.getPersonaResponsable().getP().getEdad() < 18){
                throw new ValidationException("La persona responsable debe ser mayor de edad");
            }
        } 
    }
    
}
