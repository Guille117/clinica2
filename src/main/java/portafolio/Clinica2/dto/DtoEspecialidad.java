package portafolio.Clinica2.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class DtoEspecialidad {
    @NotNull
    private Long idEspecialidad;

    private String nombre;
    private Double precio;
}
