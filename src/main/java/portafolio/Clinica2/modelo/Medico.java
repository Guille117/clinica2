package portafolio.Clinica2.modelo;

import org.springframework.boot.context.properties.bind.DefaultValue;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import portafolio.Clinica2.Enum.Especialidad;

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
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    
}
