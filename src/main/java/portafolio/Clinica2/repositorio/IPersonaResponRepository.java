package portafolio.Clinica2.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import portafolio.Clinica2.modelo.PersonaResponsable;

@Repository
public interface IPersonaResponRepository extends JpaRepository<PersonaResponsable, Long>{
    
}
