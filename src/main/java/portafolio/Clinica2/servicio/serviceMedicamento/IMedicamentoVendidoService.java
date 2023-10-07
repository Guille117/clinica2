package portafolio.Clinica2.servicio.serviceMedicamento;

import java.util.List;

import portafolio.Clinica2.dto.DtoMedicamento.DtoMedVendido1;
import portafolio.Clinica2.dto.DtoMedicamento.DtoMedVendido2;
import portafolio.Clinica2.modelo.Cobro;
import portafolio.Clinica2.modelo.MedicamentoVendido;

public interface IMedicamentoVendidoService{
    void Sa(MedicamentoVendido t);
    void De(Long idMedVenta);
    DtoMedVendido2 getOneVenta(Long idMedVendido);
    List<DtoMedVendido2> getAllVenta();
    List<DtoMedVendido2> obtenerPorFecha(String fecha);
    List<DtoMedVendido2> obtenerPorMedicamento(Long idMedicamento);
    List<DtoMedVendido2> obtenerPorIdCobro(Long idCobro);

    // Acciones que se realizan desde el cobro general
    List<DtoMedVendido1> getAllCobro();
    List<DtoMedVendido1> getAllCobroId(Long idCobro);
    Double guardarTodo(List<MedicamentoVendido> medicamentos, Cobro idVentaGeneral);
    void eliminarPorIdCobroGeneral(Long idGeneral);
}
