package portafolio.Clinica2.dto.DtoCobro;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import portafolio.Clinica2.modelo.Consulta;
import portafolio.Clinica2.modelo.MedicamentoVendido;
import portafolio.Clinica2.modelo.Paciente;

@AllArgsConstructor
@NoArgsConstructor
@Getter  @Setter
public class DtoCobroIngreso {
    private List<MedicamentoVendido> medicamentoVenta;
    private Consulta consulta;
    private Paciente paciente;
}
