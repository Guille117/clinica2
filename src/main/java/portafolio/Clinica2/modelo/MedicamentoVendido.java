package portafolio.Clinica2.modelo;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private Long idVentaMedicamento;

    @NotNull
    Long idMedicamento;

    @Column(name = "nombre_medicamento")
    private String nombre;

    @Column(name = "marca_medicamento")
    private String marca;

    @Column(name = "fecha_vencimiento_medicamento")
    private LocalDate fechaVencimiento;

    @Column(name = "precio_medicamento")
    private Double precio;

    @NotNull
    @Min(1)
    private Integer cantidad;

    LocalDate fechaVenta = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "id_venta_general", referencedColumnName = "idCobro")
    private Cobro idCobroGeneral;

    private Double total;

    public void inicializarAtributos(Medicamento med){
        this.nombre = med.getNombre();
        this.marca = med.getMarca();
        this.precio = med.getPrecioUnitario();
        this.fechaVencimiento = med.getFechaVencimiento();
        this.total = this.precio * this.cantidad;
    }
}
