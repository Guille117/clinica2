package portafolio.Clinica2.dto.DtoMedicamento;

import java.time.LocalDate;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import portafolio.Clinica2.modelo.MedicamentoVendido;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Embeddable
public class DtoMedVendido2 extends DtoMedVendido1{             // este se mostrar en el registro de venta medicamento por separado
    private LocalDate fechaVencimiento;
    private LocalDate fechaVenta;
    private Long idVentaMedicamento;
    private Long idCobro;

    public DtoMedVendido2(MedicamentoVendido med){ 
        super(med);
        this.fechaVencimiento = med.getFechaVencimiento();
        this.fechaVenta = med.getFechaVenta();
        this.idVentaMedicamento = med.getIdVentaMedicamento();
        this.idCobro = med.getIdCobroGeneral().getIdCobro();
    }
}
