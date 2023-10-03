package portafolio.Clinica2.modelo;

import java.time.LocalDate;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import portafolio.Clinica2.dto.DtoMedicamentoParaVenta;

@Entity
@Getter @Setter
@NoArgsConstructor
public class MedicamentoVendido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    @NotNull
    @Embedded
    DtoMedicamentoParaVenta medicamento;

    @NotNull
    @Min(1)
    private Integer cantidad;

    LocalDate fechaVenta = LocalDate.now();

    private Double total;

    public Double calcularTotal(Medicamento m){
        return m.getPrecioUnitario() * this.cantidad;
    }

   
    
}
