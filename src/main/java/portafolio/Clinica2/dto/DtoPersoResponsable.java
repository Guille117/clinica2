package portafolio.Clinica2.dto;

import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class DtoPersoResponsable {
    private Long idResponsable;

    @Embedded
    @Valid
    private DtoPersona p;

    @Size(min = 8, max = 8, message = "Formato de número telefónico incorrecto")
    private String telefono;

    @Size(min = 13, max = 13, message = "Formato de número de identificación incorrecto")
    private String dpi;
}
