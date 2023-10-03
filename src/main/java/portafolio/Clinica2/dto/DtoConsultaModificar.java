package portafolio.Clinica2.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor 
public class DtoConsultaModificar {
    @NotNull
    private Long idConsulta;

    private Long idMedico;
    
    private Long idPaciente;

    private Long idEspecialidad;

    @Future
    private LocalDateTime fechaYHora;

}
