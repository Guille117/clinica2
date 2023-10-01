package portafolio.Clinica2.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import portafolio.Clinica2.dto.DtoPersoResponsable;
import portafolio.Clinica2.dto.DtoPersona;
import portafolio.Clinica2.modelo.Persona;
import portafolio.Clinica2.modelo.PersonaResponsable;
import portafolio.Clinica2.repositorio.IPersonaResponRepository;

@Service
public class PersoResponsableService {
    @Autowired
    private IPersonaResponRepository pr;

    public void guardar(PersonaResponsable pRes){
        pr.save(pRes);
    }

    @Transactional
    public void actualizarResponsable(DtoPersoResponsable DtoRespo){
        PersonaResponsable persoRepo = pr.findById(DtoRespo.getIdResponsable()).orElse(null);
        Persona pr = persoRepo.getP();              // se obtiene a las "Persona" registrada en la base de datos
        DtoPersona dtpr = DtoRespo.getP();          // se obtiene a la "persona" que llega por parametro

        if(dtpr != null){
            if(dtpr.getNombre() != null){
                pr.setNombre(dtpr.getNombre());
            }
            if(dtpr.getApellido() != null){
                pr.setApellido(dtpr.getApellido());
            }
            if(dtpr.getEdad() != null){
                pr.setEdad(dtpr.getEdad());
            }
            
        }if(DtoRespo.getDpi() != null){
            persoRepo.setDpi(DtoRespo.getDpi());
        }
        if(DtoRespo.getTelefono() != null){
            persoRepo.setTelefono(DtoRespo.getTelefono());
        }
    }
}
