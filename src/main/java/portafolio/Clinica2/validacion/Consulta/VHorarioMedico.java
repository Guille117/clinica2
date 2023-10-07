package portafolio.Clinica2.validacion.Consulta;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import portafolio.Clinica2.modelo.Consulta;
import portafolio.Clinica2.repositorio.IConsultaRepository;

@Component
public class VHorarioMedico implements IValidarConsulta{
    // Verificamos que el medico no tenga otro consulta en la misma fecha

    @Autowired
    private IConsultaRepository cr;

    @Override
    public void validar(Consulta c, String verbo) {
        if(verbo.equalsIgnoreCase("post")){ 

            Long idMedico = c.getMedico().getIdMedico();
            LocalDateTime fecha = c.getFechaYHora();
            
            if(cr.existsByMedicoIdMedicoAndFechaYHora(idMedico, fecha)){
                Consulta consulta = cr.findByMedicoIdMedicoAndFechaYHora(idMedico, fecha);
                if(!(consulta.getIdConsulta().equals(c.getIdConsulta()))){
                throw new ValidationException("El medico ya tiene una consulta programada para este horario.");
            }
            }
            
        }
    }
    
}
