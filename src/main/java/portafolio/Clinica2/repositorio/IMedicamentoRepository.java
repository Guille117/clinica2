package portafolio.Clinica2.repositorio;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import portafolio.Clinica2.modelo.Medicamento;

@Repository
public interface IMedicamentoRepository extends JpaRepository<Medicamento, Long>{
    public abstract List<Medicamento> findByMarca(String marca);
    public abstract List<Medicamento> findByFechaVencimientoBetween(LocalDate hoy, LocalDate meses2);
    public abstract List<Medicamento> findByFechaVencimientoBefore(LocalDate hoy);
    public abstract List<Medicamento> findByCanDisponible(Integer dis);
}
