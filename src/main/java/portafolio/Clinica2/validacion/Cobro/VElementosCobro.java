package portafolio.Clinica2.validacion.Cobro;

import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import portafolio.Clinica2.dto.DtoCobro.DtoCobroIngreso;

@Component
public class VElementosCobro implements IValidCobro{
    // en caso no hubise consulta ni medicamentos

    @Override
    public void validarCobro(DtoCobroIngreso dc) {
        if(dc.getConsulta() == null && dc.getMedicamentoVenta() == null){
            throw new ValidationException("Sin elementos a cobrar");
        }
    }
    
}
