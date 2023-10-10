package portafolio.Clinica2.validacion.Consulta;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import portafolio.Clinica2.modelo.Consulta;
import portafolio.Clinica2.repositorio.IConsultaRepository;

@Component
public class VPaciente implements IValidarConsulta{
    // Cada paciente unicamente puede realizar una consulta por día

    @Autowired
    private IConsultaRepository cr;

    @Override
    public void validar(Consulta c) {
            Long idPaciente = c.getPaciente().getIdPaciente();
            LocalDateTime fecha = c.getFechaYHora();
            LocalDateTime mañana = fecha.withHour(7);
            LocalDateTime tarde = fecha.withHour(17);

            

            if(cr.existsByPacienteIdPacienteAndFechaYHoraBetween(idPaciente, mañana, tarde)){
                Consulta consulta = cr.findByPacienteIdPacienteAndFechaYHoraBetween(idPaciente, mañana, tarde);
                 if(!(consulta.getIdConsulta().equals(c.getIdConsulta()))){
                throw new ValidationException("Cada paciente únicamente puede realizar una consulta por día");
            }
           
        }
    }


}


