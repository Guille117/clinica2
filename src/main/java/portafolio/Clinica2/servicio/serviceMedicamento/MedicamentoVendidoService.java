package portafolio.Clinica2.servicio.serviceMedicamento;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import portafolio.Clinica2.dto.DtoMedicamento.DtoMedVendido1;
import portafolio.Clinica2.dto.DtoMedicamento.DtoMedVendido2;
import portafolio.Clinica2.modelo.Cobro;
import portafolio.Clinica2.modelo.Medicamento;
import portafolio.Clinica2.modelo.MedicamentoVendido;
import portafolio.Clinica2.repositorio.IMedicamentoVendidoRepository;

@Service
public class MedicamentoVendidoService implements IMedicamentoVendidoService{

    @Autowired
    private IMedicamentoVendidoRepository mvr;
    @Autowired
    private MedicamentoService ms;

    @Override
    @Transactional
    public void Sa(MedicamentoVendido t) {
        Medicamento med = ms.getOne(t.getIdMedicamento());
        Integer disponible = med.getCanDisponible();
       if( disponible >= t.getCantidad()){      // comporbamos que haya suficiente medicamento
            disponible -= t.getCantidad();      // hacemos el descuento de la cantidad disponible
            med.setCanDisponible(disponible);       // agregamos la nueva cantidad disponible a medicamento 

            //t.setTotal(med.getPrecioUnitario() * t.getCantidad());       // calculamos y seteamos el total
            
            ms.Sa(med);     // actualizamos medicamento
            t.inicializarAtributos(med);        // inicializamos los atributos 
            mvr.save(t);        // guardamos medicamento vendido
       }else{
        throw new ValidationException("No se cuenta con suficiente medicamento, nombre: " + med.getNombre());
       }
    }

    @Override
    public DtoMedVendido2 getOneVenta(Long id) {
        MedicamentoVendido medicamento = mvr.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontró el registro de venta"));
        return new DtoMedVendido2(medicamento);
    }


    @Override
    public List<DtoMedVendido2> getAllVenta() {
       List<MedicamentoVendido> medicamentos = mvr.findAll();
       List<DtoMedVendido2> dtoMedicamentos = medicamentos.stream().map(DtoMedVendido2::new).collect(Collectors.toList());
       
        return dtoMedicamentos;
    }

    @Override
    public List<DtoMedVendido1> getAllCobro() {
       List<MedicamentoVendido> medicamentos = mvr.findAll();
       List<DtoMedVendido1> dtoMedicamentos = medicamentos.stream().map(DtoMedVendido1::new).collect(Collectors.toList());
       
        return dtoMedicamentos;
    }

    @Override
    public void De(Long id) {
    }

    @Override
    public List<DtoMedVendido2> obtenerPorFecha(String fecha) {
        List<MedicamentoVendido> medicamentos = mvr.findByFechaVenta(LocalDate.parse(fecha));
        List<DtoMedVendido2> dtoMedicamentos = medicamentos.stream().map(DtoMedVendido2::new).collect(Collectors.toList());
        return dtoMedicamentos;
    }

    @Override
    public List<DtoMedVendido2> obtenerPorMedicamento(Long idMedicamento) {
        List<MedicamentoVendido> medicamentos = mvr.findByIdMedicamento(idMedicamento);
        List<DtoMedVendido2> dtoMedicamentos = medicamentos.stream().map(DtoMedVendido2::new).collect(Collectors.toList());
        return dtoMedicamentos;

    }

    @Override
    @Transactional
    public Double guardarTodo(List<MedicamentoVendido> medicamentos, Cobro idVentaGeneral) {
        Double total = 0D;
        for(MedicamentoVendido m: medicamentos){
            m.setIdCobroGeneral(idVentaGeneral);
            this.Sa(m);
            total += m.getTotal();
        }

        return total;
    }

    @Override
    public List<DtoMedVendido1> getAllCobroId(Long idCobro) {
        List<MedicamentoVendido> medVendido = mvr.findByIdCobroGeneralIdCobro(idCobro);
        List<DtoMedVendido1> dtoMedicamentos = medVendido.stream().map(DtoMedVendido1::new).collect(Collectors.toList()); 
        return dtoMedicamentos;
    }

    @Override
    public void eliminarPorIdCobroGeneral(Long idGeneral) {
       mvr.deleteByIdCobroGeneralIdCobro(idGeneral);
    }

    @Override
    public List<DtoMedVendido2> obtenerPorIdCobro(Long idCobro) {
        List<MedicamentoVendido> medVendido = mvr.findByIdCobroGeneralIdCobro(idCobro);
        List<DtoMedVendido2> dtoMedicamentos = medVendido.stream().map(DtoMedVendido2::new).collect(Collectors.toList()); 
        return dtoMedicamentos;
    }
    
}
