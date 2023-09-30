package portafolio.Clinica2.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
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
    public void Sa(MedicamentoVendido t) {
        Medicamento med = ms.getOne(t.getMedicamento().getIdMedicamento());
        Integer disponible = med.getCanDisponible();
       if( disponible >= t.getCantidad()){
            disponible -= t.getCantidad();
            //t.getMedicamento().setCanDisponible(disponible);
            t.setTotal(t.calcularTotal(med));

            System.out.println(t.getTotal());
            //mvr.save(t);
       }else{
        throw new ValidationException("No se cuenta con suficiente " + t.getMedicamento().getNombre());
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
