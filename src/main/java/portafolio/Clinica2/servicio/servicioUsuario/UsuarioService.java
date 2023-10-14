package portafolio.Clinica2.servicio.servicioUsuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.Null;
import portafolio.Clinica2.modelo.Funcion;
import portafolio.Clinica2.modelo.MUsuario;
import portafolio.Clinica2.repositorio.IFuncionRepository;
import portafolio.Clinica2.repositorio.IMUsuarioRepository;
import portafolio.Clinica2.servicio.IGenericService;

@Service
public class UsuarioService implements IGenericService<MUsuario>{

    @Autowired
    private IMUsuarioRepository mur;
    @Autowired
    private PasswordEncoder passEncoder;
    @Autowired IFuncionRepository fr;


    @Override
    public void Sa(MUsuario t) {
        t.setContraseña(passEncoder.encode(t.getContraseña()));     // seteamos contraseña encriptada
        Funcion f = null;
        if(t.getFuncion().getIdFuncion() != null){      // si tenemos id, lo buscamos por id y guardamos 
            f = fr.findById(t.getFuncion().getIdFuncion()).orElseThrow(() -> new ValidationException("Función no encontrada"));
        }else{
            if(t.getFuncion().getNombreFuncion() != null){      // si no hay id pero hay nombre de función
                f = fr.findByNombreFuncion(t.getFuncion().getNombreFuncion());
            }
        }
        
        if(f != null){      // si existe la función en la base de datos lo seteamos al usuario
            t.setFuncion(f);
        }else{      // si no existe se crea automáticamente pero es necesario que se envíe un nombre de funcion
            if(t.getFuncion().getNombreFuncion() == null){      
                throw new ValidationException("Proporcione un nombre de función");
            }
            
        }


        mur.save(t);
    }

    @Override
    public MUsuario getOne(Long id) {
       return mur.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    }

    @Override
    public List<MUsuario> getAll() {
        return mur.findAll();
    }

    @Override
    public void De(Long id) {
        mur.deleteById(id);
    }
    
}
