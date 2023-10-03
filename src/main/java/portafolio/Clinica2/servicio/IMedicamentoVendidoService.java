package portafolio.Clinica2.servicio;

import java.util.List;

import portafolio.Clinica2.modelo.MedicamentoVendido;

public interface IMedicamentoVendidoService extends IGenericService<MedicamentoVendido>{
    List<MedicamentoVendido> obtenerPorFecha(String fecha);
    List<MedicamentoVendido> obtenerPorMedicamento(Long idMedicamento);
}
