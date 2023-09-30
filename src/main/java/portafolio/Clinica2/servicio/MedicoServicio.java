package portafolio.Clinica2.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import portafolio.Clinica2.dto.DtoMedico;
import portafolio.Clinica2.modelo.Medico;
import portafolio.Clinica2.repositorio.IMedicoRepository;

@Service
public class MedicoServicio implements IGenericService<Medico, DtoMedico>{

    @Autowired
    private IMedicoRepository mr;

    @Override
    public void Sa(Medico t) {
        mr.save(t);
    }

    @Override
    public Medico getOne(Long id) {
        return mr.findById(id).orElseThrow(() -> new EntityNotFoundException("Médico no encontrado"));
    }

    @Override
    public List<Medico> getAll() {
       return mr.findAll(); 
    }

    @Override
    public void Up(DtoMedico dtoMedico) {
       
    }

    @Override
    public void De(Long id) {
       mr.deleteById(id);
    }
    
}
