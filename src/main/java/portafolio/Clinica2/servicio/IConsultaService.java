package portafolio.Clinica2.servicio;

import java.util.List;

import portafolio.Clinica2.dto.DTOconsulta;
import portafolio.Clinica2.modelo.Consulta;

public interface IConsultaService {
    void Sa(Consulta c);
    DTOconsulta getOne(Long id);
    List<DTOconsulta> getAll();
    void De(Long id);
}
