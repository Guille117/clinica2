package portafolio.Clinica2.dto.DtoMedicamento;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import portafolio.Clinica2.modelo.MedicamentoVendido;

@NoArgsConstructor
@Getter @Setter
public class DtoMedVendido1 {       // esto se mostrará en el cobro general

    @NotNull
    private Long idMedicamento;
    private String nombre;
    private String marca;
    private Double precio;
    private Integer cantidad;
    private Double total;

    public DtoMedVendido1(MedicamentoVendido med) {
        this.idMedicamento = med.getIdMedicamento();
        this.nombre = med.getNombre();
        this.marca = med.getMarca();
        this.precio = med.getPrecio();
        this.cantidad = med.getCantidad();
        this.total = med.getTotal();
    }

}
