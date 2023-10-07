package portafolio.Clinica2.repositorio;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import portafolio.Clinica2.modelo.MedicamentoVendido;

@Repository
public interface IMedicamentoVendidoRepository extends JpaRepository<MedicamentoVendido, Long>{
    public abstract List<MedicamentoVendido> findByFechaVenta(LocalDate fecha);
    public abstract List<MedicamentoVendido> findByIdMedicamento(Long idMedicamento);
    public abstract List<MedicamentoVendido> findByIdCobroGeneralIdCobro(Long idCobro);
    public abstract void deleteByIdCobroGeneralIdCobro(Long idCobro);
    
}
