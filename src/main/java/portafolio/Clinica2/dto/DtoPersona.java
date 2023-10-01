package portafolio.Clinica2.dto;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class DtoPersona {
    private String nombre;
    private String apellido;
    @Min(value = 0, message = "Edad fuera de rango permitido")
    @Max(value = 100, message = "Edad fuera de rango permitido")
    private Integer edad;
}
