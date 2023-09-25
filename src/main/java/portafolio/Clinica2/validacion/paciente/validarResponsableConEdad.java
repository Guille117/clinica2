package portafolio.Clinica2.validacion.paciente;

import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import portafolio.Clinica2.modelo.Paciente;

@Component
public class validarResponsableConEdad implements ValidarPaciente{
    public void validar(Paciente pa){
        if(pa.getP().getEdad() < 18 && pa.getPersonaResponsable() == null){
            throw new ValidationException("El paciente debe tener un adulto responsable ya que es menor de edad");
        }
    }
}
