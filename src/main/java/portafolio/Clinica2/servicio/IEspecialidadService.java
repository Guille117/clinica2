package portafolio.Clinica2.servicio;

import portafolio.Clinica2.dto.DtoEspecialidad;
import portafolio.Clinica2.modelo.Especialidad;

public interface IEspecialidadService extends IGenericService<Especialidad>{
    void Up(DtoEspecialidad dtoEsp);
}
