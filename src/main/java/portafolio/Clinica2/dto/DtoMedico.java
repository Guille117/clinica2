package portafolio.Clinica2.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoMedico {
    @NotNull
    private Long idMedico;

    @Valid
    private DtoPersona p;

    @Size(min = 8, max = 8, message = "Formato de número telefónico incorrecto")
    private String telefono;
    
    @Size(min = 13, max = 13, message = "Formato de numero de identificación incorrecto")
    private String dpi;

    private Long idEspecialidad;
}
