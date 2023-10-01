package portafolio.Clinica2.servicio;

import java.util.List;

import portafolio.Clinica2.dto.DtoMedicamento;
import portafolio.Clinica2.modelo.Medicamento;

public interface IMedicamentoService extends IGenericService<Medicamento, DtoMedicamento>{
    List<Medicamento> medicamentoPorMarca(String Marca);
    List<Medicamento> medicamentoACaducar();
    List<Medicamento> medicamentoCaducado();
    List<Medicamento> medicamentoSinExistencia();
}
