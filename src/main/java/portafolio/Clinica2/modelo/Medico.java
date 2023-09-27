package portafolio.Clinica2.modelo;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedico;

    @Embedded
    @Valid
    private Persona p;
    
    private Boolean estado = true;

    @NotNull
    @Size(min = 8, max = 8, message = "Formato de número telefónico incorrecto")
    private String telefono;
    
    @NotNull
    @Size(min = 13, max = 13, message = "Formato de numero de identificación incorrecto")
    private String dpi;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_especialidad", referencedColumnName = "idEspecialidad")
    private Especialidad especialidad;
    
}
