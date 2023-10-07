package portafolio.Clinica2.servicio.serviceEspecialidad;

import portafolio.Clinica2.dto.DtoEspecialidad;
import portafolio.Clinica2.modelo.Especialidad;
import portafolio.Clinica2.servicio.IGenericService;

public interface IEspecialidadService extends IGenericService<Especialidad>{
    void Up(DtoEspecialidad dtoEsp);
}
