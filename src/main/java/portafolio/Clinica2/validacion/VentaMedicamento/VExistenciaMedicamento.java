package portafolio.Clinica2.validacion.VentaMedicamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import portafolio.Clinica2.modelo.Medicamento;
import portafolio.Clinica2.modelo.MedicamentoVendido;
import portafolio.Clinica2.servicio.serviceMedicamento.IMedicamentoService;

@Component
public class VExistenciaMedicamento implements IVVentaMedicamento {
        // validamos que la farmacia tenga la cantidad suficiente del medicamento

    @Autowired
    private IMedicamentoService ims;
    
    @Override
    public void validarVentaMedicamento(MedicamentoVendido med) {
        Medicamento medicamento = ims.getOne(med.getIdMedicamento());
        if(medicamento.getCanDisponible() < med.getCantidad()){
            throw new ValidationException("No se cuenta con la cantidad suficiente del medicamento: " + medicamento.getNombre());
        }
    }
    
}
