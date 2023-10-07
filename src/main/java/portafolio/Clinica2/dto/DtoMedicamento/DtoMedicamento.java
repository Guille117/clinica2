package portafolio.Clinica2.dto.DtoMedicamento;

import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoMedicamento {           // este dto sirve para ingresar los atributos que deseeamos actualizar
    @NotNull
    private Long idMedicamento;
    private String nombre;
    private String marca;
    @Future
    private LocalDate fechaVencimiento;
    private Integer canDisponible;
    @DecimalMin(value = "0.25")
    private Double precioUnitario;
}
