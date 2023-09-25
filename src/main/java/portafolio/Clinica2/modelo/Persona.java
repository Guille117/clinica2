package portafolio.Clinica2.modelo;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotNull
    @Min(value = 0, message = "Edad fuera de rango permitido")
    @Max(value = 100, message = "Edad fuera de rango permitido")
    private Integer edad;
}
