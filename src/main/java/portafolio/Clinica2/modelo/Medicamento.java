package portafolio.Clinica2.modelo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Long IdMedicamento;

    @NotBlank
    private String nombre;
    
    @NotBlank
    private String marca;
    
    private LocalDate fechaIngreso;         // auto generado
    
    @NotNull
    private LocalDate fechaVencimiento;

    @NotNull
    private Integer canDisponible;

    @NotNull
    private Double precioUnitario;

}
