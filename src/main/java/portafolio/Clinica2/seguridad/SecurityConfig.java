package portafolio.Clinica2.seguridad;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import portafolio.Clinica2.seguridad.filtros.JwtAutorizacionFiltro;
import portafolio.Clinica2.seguridad.filtros.JwtFiltroAutenticacion;
import portafolio.Clinica2.seguridad.jwt.JwtUtils;
import portafolio.Clinica2.servicio.servicioUsuario.ServiceUsuarioJWT;

import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig{

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private ServiceUsuarioJWT servicioUsuario;
    @Autowired
    private JwtAutorizacionFiltro filtroAuto;

    
    @Bean       // configuramos el comportamiento de acceso a los endpoint 
    public SecurityFilterChain filtro(HttpSecurity http, AuthenticationManager gestionAutenticacion) throws Exception{
        
        JwtFiltroAutenticacion jwtAuthenticationFilter = new JwtFiltroAutenticacion(jwtUtils);
        jwtAuthenticationFilter.setAuthenticationManager(gestionAutenticacion);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");
        
        http
        .csrf(csrf -> csrf.disable())
         .authorizeHttpRequests(authorize ->{
            //authorize.requestMatchers("/ingreso").permitAll();
            authorize.anyRequest().authenticated();         // para acceder a cualquier request hay que autenticarse
        })
        .sessionManagement(session -> {
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        })
        //.httpBasic(basic -> basic.toString());
        .addFilter(jwtAuthenticationFilter)
        .addFilterBefore(filtroAuto, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /* 
    @Bean       // configuramos un usuario en memoria
    UserDetailsService servUsuario(){
        InMemoryUserDetailsManager admUsuario = new InMemoryUserDetailsManager();
        //User use = new User("guille", "117",  null);      // de esta forma tambien se puede
        admUsuario.createUser(User.withUsername("guille")
        .password("117")
        .roles()
        .build());

        //admUsuario.createUser(use);
        return admUsuario;
    }*/

    @Bean       // se configura la encriptacion
    PasswordEncoder codificadora(){
        //return NoOpPasswordEncoder.getInstance();       // indica que no utilizará ninguna clase de encriptación esto solo se hace con fines de prueba;
        return new BCryptPasswordEncoder();
    } 

    @Bean       // administra la autenticación del usuario para ello dentro del metodo al servicio de usuario y administrado de encriptacion
    AuthenticationManager autenticacion(HttpSecurity security, PasswordEncoder codificadora) throws Exception{
        return security.getSharedObject(AuthenticationManagerBuilder.class)
        .userDetailsService(servicioUsuario)
        .passwordEncoder(codificadora)
        .and()
        .build();
    }
}
