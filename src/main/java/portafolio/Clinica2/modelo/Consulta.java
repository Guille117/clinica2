package portafolio.Clinica2.modelo;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsulta;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idMedico", referencedColumnName = "idMedico")
    private Medico medico;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idPaciente",  referencedColumnName = "idPaciente")
    private Paciente paciente;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idEspecialidad", referencedColumnName = "idEspecialidad")
    private Especialidad especialidad;

    @NotNull
    private LocalDateTime fecha_Y_Hora;
}
