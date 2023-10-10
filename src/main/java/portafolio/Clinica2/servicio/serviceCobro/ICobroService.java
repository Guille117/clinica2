package portafolio.Clinica2.servicio.serviceCobro;
import java.util.List;

import portafolio.Clinica2.dto.DtoCobro.DtoCobroIngreso;
import portafolio.Clinica2.dto.DtoCobro.DtoMostrarCobro;
import portafolio.Clinica2.modelo.Cobro;
import portafolio.Clinica2.servicio.IGenericService;

public interface ICobroService extends IGenericService<Cobro>{
    Long preRegistro(DtoCobroIngreso dtoCobro);
    DtoMostrarCobro mostrarCobroSimple(Long id);
    List<DtoMostrarCobro> mostrarCobrosSimple();
    List<DtoMostrarCobro> mostrarPorFecha(String fecha);
    List<DtoMostrarCobro> mostrarPorPaciente(Long id);
    void anularCobro(Long idCobro);
    double mostrarTotalVentaPorDia(String fecha);
}
