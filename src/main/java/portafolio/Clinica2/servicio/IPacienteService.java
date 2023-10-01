package portafolio.Clinica2.servicio;

import java.util.List;

import portafolio.Clinica2.dto.DtoPaciente;
import portafolio.Clinica2.modelo.Paciente;

public interface IPacienteService extends IGenericService<Paciente, DtoPaciente>{
    List<Paciente> mayores();
    List<Paciente> menores();
    
}
