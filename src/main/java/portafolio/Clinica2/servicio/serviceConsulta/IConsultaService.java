package portafolio.Clinica2.servicio.serviceConsulta;


import java.util.List;

import portafolio.Clinica2.dto.DtoConsulta.DtoConsultaModificar;
import portafolio.Clinica2.dto.DtoConsulta.DtoConsultaMostrar;
import portafolio.Clinica2.dto.DtoConsulta.DtoConsultaParaCobro;
import portafolio.Clinica2.modelo.Consulta;

public interface IConsultaService {
    void Sa(Consulta c);
    DtoConsultaMostrar getOne(Long id);
    List<DtoConsultaMostrar> getAll();
    void De(Long id);
    void Up(DtoConsultaModificar dtoCon);
    Consulta getOneOriginal(Long id);       // se obtienen todos los datos
    List<DtoConsultaParaCobro> getAllMedico(Long idMedico);
    List<DtoConsultaParaCobro> getAllEspecialidad(Long idEspecialidad);
    List<DtoConsultaParaCobro> getAllFecha(String fecha);
    List<DtoConsultaParaCobro> getPagadoOnO(Boolean pagado);
    void pagarConsulta(Consulta c, Long idGeneral);
}
