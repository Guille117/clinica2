package portafolio.Clinica2.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
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
    @JoinColumn(name = "id_Medico", referencedColumnName = "idMedico")
    private Medico medico;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_Paciente",  referencedColumnName = "idPaciente")
    private Paciente paciente;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_Especialidad", referencedColumnName = "idEspecialidad")
    private Especialidad especialidad;

    @NotNull
    @Column(name = "fecha_y_hora")
    @Future
    private LocalDateTime fechaYHora;

    boolean pagado = false;
    LocalDate fechaPago = null;

}
