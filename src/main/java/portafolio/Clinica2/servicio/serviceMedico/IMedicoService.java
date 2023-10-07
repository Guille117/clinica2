package portafolio.Clinica2.servicio.serviceMedico;

import java.util.List;

import portafolio.Clinica2.dto.DtoMedico;
import portafolio.Clinica2.modelo.Medico;
import portafolio.Clinica2.servicio.IGenericService;

public interface IMedicoService extends IGenericService<Medico>{
    void Up(DtoMedico dtoMedico);
    void activarMedico(Long id);
    void desactivarMedico(Long id);
    List<Medico> activos(boolean estado);
    List<Medico> medEspecialidad(String especialidad);
    
}
