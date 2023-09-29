package portafolio.Clinica2.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import portafolio.Clinica2.dto.DTOconsulta;
import portafolio.Clinica2.modelo.Consulta;
import portafolio.Clinica2.repositorio.IConsultaRepository;

@Service
public class ConsultaService implements IConsultaService{
    
    @Autowired
    private IConsultaRepository cr;

    @Override
    public void Sa(Consulta t) {
       cr.save(t);
    }

    @Override
    public DTOconsulta getOne(Long id) {
        return new DTOconsulta(cr.findById(id).orElseThrow(() -> new EntityNotFoundException("Consulta no encontrada")));
    }


    @Override
    public List<DTOconsulta> getAll() {
        
        List<Consulta> consultas = cr.findAll();
        List<DTOconsulta> consultasDto = new ArrayList();

        for(Consulta c: consultas){
            consultasDto.add(new DTOconsulta(c));
        }
        
        return consultasDto;
    }

    @Override
    public void De(Long id) {
       cr.deleteById(id);
    }
}
