package portafolio.Clinica2.dto;

import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoPaciente {
    @NotNull
    private Long idPaciente;

    @Embedded
    @Valid
    private DtoPersona p;

    @Size(min = 8, max = 8, message = "Formato de número telefónico incorrecto")
    private String telefono;

    @Size(min = 13, max = 13, message = "Formato de número de identificación incorrecto")
    private String dpi;
}
