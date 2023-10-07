package portafolio.Clinica2.Utilidades;

import java.util.ArrayList;
import java.util.List;

import portafolio.Clinica2.modelo.MedicamentoVendido;

public class UMedicamentosRepetidos {
    public static List<MedicamentoVendido> unificarMedicamentos(List<MedicamentoVendido> medicamentos){
        List<MedicamentoVendido> resultado = new ArrayList<>();
        while(!(medicamentos.isEmpty())){
            MedicamentoVendido med = medicamentos.get(0);
            for(int i=1; i<medicamentos.size(); i++){
                if(med.getIdMedicamento().equals(medicamentos.get(i).getIdMedicamento())){
                    med.setCantidad(med.getCantidad() + medicamentos.get(i).getCantidad());
                    medicamentos.remove(i);
                }
                
            }
            resultado.add(med);
            medicamentos.remove(0);
            
        }
        return resultado;
    }
}
