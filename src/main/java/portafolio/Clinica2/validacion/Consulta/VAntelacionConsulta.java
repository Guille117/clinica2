package portafolio.Clinica2.validacion.Consulta;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import portafolio.Clinica2.modelo.Consulta;

@Component
public class VAntelacionConsulta implements IValidarConsulta{
    // validamos que la creacion, modificación o cancelación de una consulta se realice 24 horas antes

    @Override
    public void validar(Consulta c, String verbo) {
       
        LocalDateTime hoy = LocalDateTime.now();
        LocalDateTime fechaConsulta = c.getFechaYHora();
        fechaConsulta = fechaConsulta.minusDays(1);
        
        if(hoy.isAfter(fechaConsulta)){
            throw new ValidationException("Las consultas se registran o eliminan 24 horas antes");
       }
        
    }

}
