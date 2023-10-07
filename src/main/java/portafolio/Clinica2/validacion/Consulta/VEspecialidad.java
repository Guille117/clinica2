package portafolio.Clinica2.validacion.Consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import portafolio.Clinica2.modelo.Consulta;
import portafolio.Clinica2.modelo.Medico;
import portafolio.Clinica2.servicio.serviceMedico.IMedicoService;

@Component
public class VEspecialidad implements IValidarConsulta{
    
    // validar que la especialidad coincida con la especialidad del médico

    @Autowired
    private IMedicoService mr;

    @Override
    public void validar(Consulta c, String verbo) {
        if(verbo.equalsIgnoreCase("post") || verbo.equalsIgnoreCase("put")){
            Medico med = mr.getOne(c.getMedico().getIdMedico());
            if(!(med.getEspecialidad().getIdEspecialidad().equals(c.getEspecialidad().getIdEspecialidad()))){
                throw new ValidationException("Médico no capacitado para la especialidad de la consulta");
        }
        }
    }

}
