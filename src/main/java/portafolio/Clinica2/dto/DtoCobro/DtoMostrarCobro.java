package portafolio.Clinica2.dto.DtoCobro;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import portafolio.Clinica2.dto.DtoConsulta.DtoConsultaParaCobro;
import portafolio.Clinica2.dto.DtoMedicamento.DtoMedVendido1;
import portafolio.Clinica2.modelo.Cobro;

@NoArgsConstructor
@Getter @Setter
public class DtoMostrarCobro {
    private Long idCobro;
    private Long idPaciente;
    private String nombrePaciente;
    private DtoConsultaParaCobro consulta;
    private Integer cantidadMedicamento;
    private List<DtoMedVendido1> medicamentos;
    private Double total;
     private LocalDate fechaCobro;
   

    public DtoMostrarCobro(Cobro cobro, List<DtoMedVendido1> med){
        this.idCobro = cobro.getIdCobro();
        this.idPaciente = cobro.getIdPaciente();
        this.nombrePaciente = cobro.getNombrePaciente();
        this.consulta = (cobro.getConsulta() != null) ? new DtoConsultaParaCobro(cobro.getConsulta()) : null;
        this.cantidadMedicamento = (cobro.getCantidadMedicamentos() != null) ? cobro.getCantidadMedicamentos() : 0;
        this.fechaCobro = cobro.getFechaCobro();
        this.total = cobro.getTotal();
        this.medicamentos = med;
    }
}
