package portafolio.Clinica2.validacion.ActualizarPaciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import portafolio.Clinica2.dto.DtoPaciente;
import portafolio.Clinica2.repositorio.IPacienteRepository;

@Component
public class perResponsable implements ValidarActualizarPaciente{
    @Autowired
    private IPacienteRepository pr;

    @Override
    public void ValidarPaciente(DtoPaciente pacDto) {
        if(!(pr.existsByIdPacienteAndPersonaResponsableIsNotNull(pacDto.getIdPaciente()))){
            if(pacDto.getP() != null && pacDto.getP().getEdad() != null && pacDto.getPersonaResponsable() == null){
                if(pacDto.getP().getEdad() < 18){
                throw new ValidationException("El paciente debe ser mayor de edad o tener una persona responable");
            }
            }
            
        }
    }
    

    //
}
