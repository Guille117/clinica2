package portafolio.Clinica2.validacion.Consulta;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import portafolio.Clinica2.dto.DtoConsulta.DtoConsultaModificar;
import portafolio.Clinica2.modelo.Consulta;
import portafolio.Clinica2.repositorio.IConsultaRepository;

@Component
public class VCambioPacienteHorario implements IValidarActualizarconsulta{
    // validamos que al cambiar al paciente, la fecha o ambos cumplir con la regla de que un paciente solo
    // puede tener una consulta por dia.

    @Autowired
    private IConsultaRepository cr;

    @Override
    public void validarActualizacion(DtoConsultaModificar dto) {
        Consulta consultaRepo = null;


        if(dto.getIdPaciente() != null){
            if(dto.getFechaYHora() != null){
                LocalDateTime mañana = dto.getFechaYHora().withHour(7).withMinute(0).withSecond(0);
                LocalDateTime tarde = dto.getFechaYHora().withHour(17).withMinute(0).withSecond(0);
                consultaRepo = cr.findByPacienteIdPacienteAndFechaYHoraBetween(dto.getIdPaciente(), mañana, tarde);
            }else{
                Consulta con = cr.findById(dto.getIdConsulta()).orElseThrow(() -> new EntityNotFoundException("Consulta no encontrada"));
                LocalDateTime mañana = con.getFechaYHora().withHour(7);
                LocalDateTime tarde = con.getFechaYHora().withHour(17);
                consultaRepo = cr.findByPacienteIdPacienteAndFechaYHoraBetween(dto.getIdPaciente(), mañana, tarde);
            }
        }else{
            if(dto.getFechaYHora() != null){
                Consulta con2 = cr.findById(dto.getIdConsulta()).orElseThrow(() -> new ValidationException("Consulta no encontrada"));
                
                LocalDateTime mañana = dto.getFechaYHora().withHour(7).withMinute(0).withSecond(0);
                LocalDateTime tarde = dto.getFechaYHora().withHour(17).withMinute(0).withSecond(0);
                consultaRepo = cr.findByPacienteIdPacienteAndFechaYHoraBetween(con2.getPaciente().getIdPaciente(), mañana, tarde);
            }
        }

        if(consultaRepo != null){
            if(consultaRepo.getIdConsulta() != dto.getIdConsulta()){
                throw new ValidationException("Cada paciente solo puede agendar una consulta por día");
            }
        }
    }
}
