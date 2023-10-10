package portafolio.Clinica2.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import portafolio.Clinica2.modelo.Paciente;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Long>{
    public abstract List<Paciente> findByPEdadGreaterThan(Integer edad);
    public abstract List<Paciente> findByPEdadLessThan(Integer edad);
    
}
