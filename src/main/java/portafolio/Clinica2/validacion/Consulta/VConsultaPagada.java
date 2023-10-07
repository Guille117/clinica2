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
public class VConsultaPagada implements IValidarActualizarconsulta{
    // si la consulta ya fue cancelada no se podra alterar
    
    @Autowired 
    private IConsultaRepository cs;


    @Override
    public void validarActualizacion(DtoConsultaModificar dto) {
        Consulta consulta = cs.findById(dto.getIdConsulta()).orElseThrow(() -> new EntityNotFoundException("Consulta no encontrada"));
        Boolean fechaCorrecta = LocalDateTime.now().isBefore(consulta.getFechaYHora().minusHours(1));

        if(consulta.isPagado() && fechaCorrecta){
            throw new ValidationException("Solo se permite modificar una consulta ya pagada 1 hora antes");
        }
    }
    


}
