package portafolio.Clinica2.servicio.serviceMedicamento;

import java.util.List;

import portafolio.Clinica2.dto.DtoMedicamento.DtoMedicamento;
import portafolio.Clinica2.modelo.Medicamento;
import portafolio.Clinica2.servicio.IGenericService;

public interface IMedicamentoService extends IGenericService<Medicamento>{
    void Up(DtoMedicamento dtoMed);
    List<Medicamento> medicamentoPorMarca(String Marca);
    List<Medicamento> medicamentoACaducar();
    List<Medicamento> medicamentoCaducado();
    List<Medicamento> medicamentoSinExistencia();
}
