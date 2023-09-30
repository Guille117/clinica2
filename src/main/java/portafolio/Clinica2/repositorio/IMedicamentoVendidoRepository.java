package portafolio.Clinica2.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import portafolio.Clinica2.modelo.MedicamentoVendido;

@Repository
public interface IMedicamentoVendidoRepository extends JpaRepository<MedicamentoVendido, Long>{
    
}
