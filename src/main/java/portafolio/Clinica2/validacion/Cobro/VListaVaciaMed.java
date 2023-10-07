package portafolio.Clinica2.validacion.Cobro;

import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import portafolio.Clinica2.dto.DtoCobro.DtoCobroIngreso;
@Component
public class VListaVaciaMed implements IValidCobro{
    // en caso se reciba una lista de medicamentos vacia   -> vacio, no nula  

    @Override
    public void validarCobro(DtoCobroIngreso dc) {
        if(dc.getMedicamentoVenta() != null){
            if(dc.getMedicamentoVenta().isEmpty()){
                throw new ValidationException("La lista de medicamentos esta vacía.");
            }
        }
        
    }
    
}
