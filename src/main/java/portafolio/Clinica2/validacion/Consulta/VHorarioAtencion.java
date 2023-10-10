package portafolio.Clinica2.validacion.Consulta;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import portafolio.Clinica2.modelo.Consulta;

@Component
public class VHorarioAtencion implements IValidarConsulta{

    @Override
    public void validar(Consulta c) {
            LocalDateTime fechaConsulta = c.getFechaYHora();

            if(fechaConsulta.getDayOfWeek() == DayOfWeek.SATURDAY || fechaConsulta.getDayOfWeek() == DayOfWeek.SUNDAY){
                throw new ValidationException("El horario de atención es de Lunes a Viernes de 08:00 a 16:00 horas");
            }

            if(fechaConsulta.getHour() < 8 || fechaConsulta.getHour() >16){
                throw new ValidationException("El horario de atención es de Lunes a Viernes de 08:00 a 16:00 horas");
            }
    }
    
}
