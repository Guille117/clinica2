package portafolio.Clinica2.validacion.Cobro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import portafolio.Clinica2.dto.DtoCobro.DtoCobroIngreso;
import portafolio.Clinica2.modelo.Consulta;
import portafolio.Clinica2.servicio.serviceConsulta.IConsultaService;

@Component
public class VConsulta implements IValidCobro{

    @Autowired
    private IConsultaService cs;

    @Override
    public void validarCobro(DtoCobroIngreso dc) {
        if(dc.getConsulta() != null){
            Consulta consultaPivote = cs.getOneOriginal(dc.getConsulta().getIdConsulta());
            if(consultaPivote.isPagado()){
                throw new ValidationException("Esta consulta ya está pagada");
            }
        }
    }
    
}
