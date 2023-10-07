package portafolio.Clinica2.modelo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Cobro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCobro;
    
    @OneToOne
    @JoinColumn(name = "id_consulta_cobrar", referencedColumnName = "idConsulta")
    private Consulta consulta;

    private Integer cantidadMedicamentos;
    private LocalDate fechaCobro = LocalDate.now();
    private Long idPaciente;
    private String nombrePaciente;
    private double total;
}
