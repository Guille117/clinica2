package portafolio.Clinica2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class DtoRetornoTotales {
    String tipoCampo;
    String valorCampo;
    Integer cantidad;
    Double total;

    public void establecerCampo(String tipoCampo, String valorCampo){
        this.setTipoCampo(tipoCampo);
        this.setValorCampo(valorCampo);
    }

    public void establecerValores(int cantidad, double total){
        this.setCantidad(cantidad);
        this.setTotal(total);
    }

}
