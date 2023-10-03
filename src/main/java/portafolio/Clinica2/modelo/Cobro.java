package portafolio.Clinica2.modelo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import portafolio.Clinica2.dto.DtoMedicamentoParaVenta;

//@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Cobro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCobro;
    
    private Consulta consulta;

    private List<DtoMedicamentoParaVenta> medicamentos;

    private double total;
}
