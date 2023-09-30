package portafolio.Clinica2.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoMedicamento {
    @NotNull
    private Long idMedicamento;
    private String nombre;
    private String marca;
    private LocalDate fechaVencimiento;
    private Integer canDisponible;
    @DecimalMin(value = "0.5")
    private Double precioUnitario;
}
