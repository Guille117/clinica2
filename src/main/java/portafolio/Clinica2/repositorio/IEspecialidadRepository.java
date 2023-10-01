package portafolio.Clinica2.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import portafolio.Clinica2.modelo.Especialidad;

@Repository
public interface IEspecialidadRepository extends JpaRepository<Especialidad, Long>{
    public abstract List<Especialidad> findByNombre(String nombre);
    
}
