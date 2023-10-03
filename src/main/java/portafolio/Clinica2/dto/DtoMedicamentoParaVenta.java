package portafolio.Clinica2.dto;

import java.time.LocalDate;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import portafolio.Clinica2.modelo.Medicamento;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Embeddable
public class DtoMedicamentoParaVenta {
    @NotNull
    private Long idMedicamento;

    private String nombreMedicamento;
    private String marcaMedicamento;
    private LocalDate fechaVencimientoMedicamento;
    private Double precioMedicamento;
    

    public void establecerValores(Medicamento med){
        this.setNombreMedicamento(med.getNombre());
        this.setMarcaMedicamento(med.getMarca());
        this.setFechaVencimientoMedicamento(med.getFechaVencimiento());
        this.setPrecioMedicamento(med.getPrecioUnitario());
    }
}
