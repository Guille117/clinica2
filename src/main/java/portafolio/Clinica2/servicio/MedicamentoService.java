package portafolio.Clinica2.servicio;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import portafolio.Clinica2.dto.DtoMedicamento;
import portafolio.Clinica2.modelo.Medicamento;
import portafolio.Clinica2.repositorio.IMedicamentoRepository;

@Service
public class MedicamentoService implements IMedicamentoService{
    @Autowired
    private IMedicamentoRepository mr;

    @Override
    public void Sa(Medicamento t) {
        mr.save(t);
    }

    @Override
    public Medicamento getOne(Long id) {
        return mr.findById(id).orElseThrow(() -> new EntityNotFoundException("Medicamento no encontrado."));
    }

    @Override
    public List<Medicamento> getAll() {
        return mr.findAll();
    }

    @Override
    @Transactional
    public void Up(DtoMedicamento dtoMed) {
        Medicamento medicamento = this.getOne(dtoMed.getIdMedicamento());

        if(dtoMed.getNombre() != null){
            medicamento.setNombre(dtoMed.getNombre());
        }
        if(dtoMed.getMarca() != null){
            medicamento.setMarca(dtoMed.getMarca());
        }
         if(dtoMed.getFechaVencimiento() != null){
            medicamento.setFechaVencimiento(dtoMed.getFechaVencimiento());
        }
        if(dtoMed.getCanDisponible() != null){
            medicamento.setCanDisponible(dtoMed.getCanDisponible());
        }
        if(dtoMed.getPrecioUnitario() != null){
            medicamento.setPrecioUnitario(dtoMed.getPrecioUnitario());
        }

        this.Sa(medicamento);
    }

    @Override
    public void De(Long id) {
        mr.deleteById(id);
    }

    @Override
    public List<Medicamento> medicamentoPorMarca(String Marca) {
        List<Medicamento> medicamentos = mr.findByMarca(Marca);
        if(medicamentos.isEmpty()){
            throw new EntityNotFoundException("Sin medicamentos de la marca " + Marca);
        }else{
            return mr.findByMarca(Marca);
        }
    }

    @Override
    public List<Medicamento> medicamentoACaducar() {
        LocalDate hoy = LocalDate.now();
        LocalDate meses2 = hoy.plusMonths(2);

       return mr.findByFechaVencimientoBetween(hoy, meses2);
    }

    @Override
    public List<Medicamento> medicamentoCaducado() {
        return mr.findByFechaVencimientoBefore(LocalDate.now());
    }

    @Override
    public List<Medicamento> medicamentoSinExistencia() {
        return mr.findByCanDisponible(0);
    }

   
}
