package portafolio.Clinica2.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import portafolio.Clinica2.dto.DtoMedicamentoVendido;
import portafolio.Clinica2.modelo.Medicamento;
import portafolio.Clinica2.modelo.MedicamentoVendido;
import portafolio.Clinica2.repositorio.IMedicamentoVendidoRepository;

@Service
public class MedicamentoVendidoService implements IGenericService<MedicamentoVendido, DtoMedicamentoVendido>{

    @Autowired
    private IMedicamentoVendidoRepository mvr;
    @Autowired
    private MedicamentoService ms;

    @Override
    @Transactional
    public void Sa(MedicamentoVendido t) {
        Medicamento med = ms.getOne(t.getMedicamento().getIdMedicamento());
        Integer disponible = med.getCanDisponible();
       if( disponible >= t.getCantidad()){
            disponible -= t.getCantidad();      // hacemos el descuento de la cantidad disponible
            med.setCanDisponible(disponible);       // agregamos la nueva cantidad disponible a medicamento 

            t.setTotal(t.calcularTotal(med));       // calculamos y seteamos el total

            t.getMedicamento().establecerValores(med); // agregamos los valores al dtoMedicamento
            
            ms.Sa(med);     // actualizamos medicamento
            mvr.save(t);        // guardamos medicamento vendido
       }else{
        throw new ValidationException("No se cuenta con suficiente medicamento, nombre: " + med.getNombre());
       }
    }

    @Override
    public MedicamentoVendido getOne(Long id) {
        return mvr.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontró el registro de venta"));
    }

    @Override
    public List<MedicamentoVendido> getAll() {
       return mvr.findAll();
    }

    @Override
    public void Up(DtoMedicamentoVendido dtoMedVen) {
    }

    @Override
    public void De(Long id) {
        mvr.deleteById(id);
    }
    
}
