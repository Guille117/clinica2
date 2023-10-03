package portafolio.Clinica2.servicio;


import java.util.List;

import portafolio.Clinica2.dto.DTOconsulta;
import portafolio.Clinica2.dto.DtoConsultaModificar;
import portafolio.Clinica2.modelo.Consulta;

public interface IConsultaService {
    void Sa(Consulta c);
    DTOconsulta getOne(Long id);
    List<DTOconsulta> getAll();
    void De(Long id);
    void Up(DtoConsultaModificar dtoCon);
    Consulta getOneOriginal(Long id);
    List<DTOconsulta> getAllMedico(Long idMedico);
    List<DTOconsulta> getAllEspecialidad(Long idEspecialidad);
    List<DTOconsulta> getAllFecha(String fecha);
    List<DTOconsulta> getPagadoOnO(Boolean pagado);
}
