package portafolio.Clinica2.validacion.Cobro;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import portafolio.Clinica2.dto.DtoCobro.DtoCobroIngreso;
import portafolio.Clinica2.modelo.Consulta;
import portafolio.Clinica2.servicio.serviceConsulta.IConsultaService;

@Component
public class VfechaConsulta implements IValidCobro{
    // indicamos que la consulta se podrá pagar el mismo día de la consulta si importar el horario.
    // no se podra pagar la consulta con más de un día de anticipación

    @Autowired
    private IConsultaService consultaService;

    @Override
    public void validarCobro(DtoCobroIngreso dc) {
        if(dc.getConsulta() != null){
            Consulta con = consultaService.getOneOriginal(dc.getConsulta().getIdConsulta());
            if(LocalDate.now().isBefore(con.getFechaYHora().toLocalDate())){
                throw new ValidationException("Se recomienda pagar la consulta, una vez haya finalizado la misma.");
            }
        }
    }
    
}
