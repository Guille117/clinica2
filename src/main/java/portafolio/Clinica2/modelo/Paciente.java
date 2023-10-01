package portafolio.Clinica2.modelo;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaciente;

    @Embedded
    @Valid
    private Persona p;

    @Size(min = 8, max = 8, message = "Formato de número telefónico incorrecto")
    private String telefono;

    @Size(min = 13, max = 13, message = "Formato de número de identificación incorrecto")
    private String dpi;

    @Valid
    @ManyToOne
    //@Cascade(CascadeType.ALL)
    @JoinColumn(name="PersonaResponsable", referencedColumnName = "idResponsable")
    private PersonaResponsable personaResponsable;
}
