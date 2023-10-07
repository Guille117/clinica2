package portafolio.Clinica2.servicio.servicePaciente;

import java.util.List;

import portafolio.Clinica2.dto.DtoPaciente;
import portafolio.Clinica2.modelo.Paciente;
import portafolio.Clinica2.servicio.IGenericService;

public interface IPacienteService extends IGenericService<Paciente>{
    void Up(DtoPaciente dtoPac);
    List<Paciente> mayores();
    List<Paciente> menores();
    
}
