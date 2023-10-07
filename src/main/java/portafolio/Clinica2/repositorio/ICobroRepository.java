package portafolio.Clinica2.repositorio;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import portafolio.Clinica2.modelo.Cobro;

public interface ICobroRepository extends JpaRepository<Cobro, Long>{
    public abstract List<Cobro> findByFechaCobro(LocalDate fecha);
    public abstract List<Cobro> findByIdPaciente(Long idPaciente);
}
