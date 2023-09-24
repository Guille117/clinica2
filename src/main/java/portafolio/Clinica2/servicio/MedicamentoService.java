package portafolio.Clinica2.servicio;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import portafolio.Clinica2.modelo.Medicamento;
import portafolio.Clinica2.repositorio.IMedicamentoRepository;

@Service
public class MedicamentoService implements IGenericService<Medicamento>{
    @Autowired
    private IMedicamentoRepository mr;

    @Override
    public void Sa(Medicamento t) {
        t.setFechaIngreso(LocalDate.now());
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
    public void Up(Long id) {
        
    }

    @Override
    public void De(Long id) {
        mr.deleteById(id);
    }
    
}
