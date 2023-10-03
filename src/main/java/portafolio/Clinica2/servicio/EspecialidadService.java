package portafolio.Clinica2.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import portafolio.Clinica2.dto.DtoEspecialidad;
import portafolio.Clinica2.modelo.Especialidad;
import portafolio.Clinica2.repositorio.IEspecialidadRepository;

@Service
public class EspecialidadService implements IEspecialidadService{

    @Autowired
    private IEspecialidadRepository er;

    @Override
    public void Sa(Especialidad t) {
       er.save(t);
    }

    @Override
    public Especialidad getOne(Long id) {
        return er.findById(id).orElseThrow(() -> new EntityNotFoundException("Especialidad no encontrada"));
    }

    @Override
    public List<Especialidad> getAll() {
        return er.findAll();
    }

    @Override
    public void Up(DtoEspecialidad dtoEsp) {
        Especialidad esp = this.getOne(dtoEsp.getIdEspecialidad());
        if(dtoEsp.getNombre() != null){
            esp.setNombre(dtoEsp.getNombre());
        }
        if(dtoEsp.getPrecio() != null){
            esp.setPrecio(dtoEsp.getPrecio());
        }
    }

    @Override
    public void De(Long id) {
       er.deleteById(id);
    }

    
    
}
