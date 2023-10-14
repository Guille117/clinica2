package portafolio.Clinica2.servicio.servicioUsuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import portafolio.Clinica2.modelo.Funcion;
import portafolio.Clinica2.modelo.MUsuario;
import portafolio.Clinica2.repositorio.IFuncionRepository;
import portafolio.Clinica2.repositorio.IMUsuarioRepository;

@Service
public class ServiceUsuarioJWT implements UserDetailsService{

    @Autowired
    private IMUsuarioRepository mur;
    @Autowired
    private IFuncionRepository fr;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       MUsuario userEntity = mur.findByAliasUsuario(username);
            
       if(userEntity == null){
            throw new UsernameNotFoundException("El usuario " + username + " no existe.");
            
        }
        Funcion f = fr.findById(userEntity.getFuncion().getIdFuncion()).orElse(null);
        List<Funcion> funciones = new ArrayList<>();

        funciones.add(f);

        Collection<? extends GrantedAuthority> authorities = funciones
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.getNombreFuncion())))
                .collect(Collectors.toSet());

        return new User(userEntity.getAliasUsuario(),
                userEntity.getContraseña(),
                true,
                true,
                true,
                true,
                authorities);
    }
    
}
