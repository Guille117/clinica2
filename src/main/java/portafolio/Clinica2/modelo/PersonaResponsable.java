package portafolio.Clinica2.modelo;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import portafolio.Clinica2.dto.DtoPersoResponsable;

@Entity
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class PersonaResponsable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResponsable;

    @Embedded
    @Valid
    private Persona p;

    @Size(min = 8, max = 8, message = "Formato de número telefónico incorrecto")
    private String telefono;

    @Size(min = 13, max = 13, message = "Formato de número de identificación incorrecto")
    private String dpi;

    public PersonaResponsable(DtoPersoResponsable v1){
        this.dpi = v1.getDpi();
        this.telefono = v1.getTelefono();
        this.p = new Persona(v1.getP());
    }
}
