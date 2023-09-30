package portafolio.Clinica2.modelo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedicamento;

    @NotBlank
    private String nombre;
    
    @NotBlank
    private String marca;
    
    private LocalDate fechaIngreso = LocalDate.now();         // auto generado
    
    @NotNull
    @Temporal(TemporalType.DATE)
    @Future
    private LocalDate fechaVencimiento;

    @NotNull
    @Min(0)
    private Integer canDisponible;

    @NotNull
    @DecimalMin(value = "0.25")
    private Double precioUnitario;

}
