package portafolio.Clinica2.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import portafolio.Clinica2.modelo.Medico;

@Repository
public interface IMedicoRepository extends JpaRepository<Medico, Long>{
    public abstract List<Medico> findByEstado(boolean estado);
    public abstract List<Medico> findByEspecialidadNombre(String nombre);
}
