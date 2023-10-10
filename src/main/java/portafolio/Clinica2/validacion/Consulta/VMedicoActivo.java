package portafolio.Clinica2.validacion.Consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import portafolio.Clinica2.modelo.Consulta;
import portafolio.Clinica2.modelo.Medico;
import portafolio.Clinica2.servicio.serviceMedico.IMedicoService;

@Component
public class VMedicoActivo implements IValidarConsulta{
    // El médico debe estar activo
    
    @Autowired
    private IMedicoService ms;

    @Override
    public void validar(Consulta c) {
            Medico med = ms.getOne(c.getMedico().getIdMedico());
        
            if(!med.getEstado()){
                throw new ValidationException("El médico esta inactivo");
            }
       
    }
    
}
