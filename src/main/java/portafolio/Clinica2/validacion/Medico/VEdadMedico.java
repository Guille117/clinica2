package portafolio.Clinica2.validacion.Medico;

import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import portafolio.Clinica2.modelo.Medico;

@Component
public class VEdadMedico implements IValidarMedico{

    @Override
    public void validarMed(Medico med) {
        if(med.getP().getEdad() < 22){
            throw new ValidationException("El médico debe ser mayor a 22 años");
        }
    }
    
}
