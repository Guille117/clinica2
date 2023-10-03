package portafolio.Clinica2.validacion.Medico;

import org.springframework.stereotype.Component;
import portafolio.Clinica2.modelo.Medico;

@Component
public interface IValidarMedico {
    void validarMed(Medico med);
}
