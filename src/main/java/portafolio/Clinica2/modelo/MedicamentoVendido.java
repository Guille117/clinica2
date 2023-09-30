package portafolio.Clinica2.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicamentoVendido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedVendido;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_medicamento", referencedColumnName = "idMedicamento")
    private Medicamento medicamento;
    
    @NotNull
    @Min(1)
    private Integer cantidad;

    private Double total;

    /*public MedicamentoVendido(Medicamento medicamento, Integer cantidad){
        this.medicamento = medicamento;
        this.cantidad = cantidad;
        this.total = this.medicamento.getPrecioUnitario() * this.cantidad;
    }*/

    public Double calcularTotal(Medicamento m){
        return m.getPrecioUnitario() * this.cantidad;
    }

   
    
}
