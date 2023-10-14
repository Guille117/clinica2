package portafolio.Clinica2.modelo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Table(name = "usuario")
public class MUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @NotNull
    @Size(max = 50)
    @Column(name = "alias")
    private String aliasUsuario;

    @NotNull
    @Email
    @Size(max = 50)
    private String correo;

    @NotNull
    private String contraseña;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_funcion", referencedColumnName = "idFuncion")
    @NotNull
    private Funcion funcion;

}
