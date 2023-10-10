package portafolio.Clinica2.repositorio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portafolio.Clinica2.modelo.Consulta;

@Repository
public interface IConsultaRepository extends JpaRepository<Consulta, Long>{
    public abstract boolean existsByMedicoIdMedicoAndFechaYHora(Long idMedico, LocalDateTime fecha);
    public abstract boolean existsByPacienteIdPacienteAndFechaYHoraBetween(Long idPaciente, LocalDateTime mañana, LocalDateTime tarde);
    public abstract Consulta findByMedicoIdMedicoAndFechaYHora(Long idMed, LocalDateTime fecha);
    public abstract Consulta findByPacienteIdPacienteAndFechaYHoraBetween(Long idPaciente, LocalDateTime mañana, LocalDateTime tarde);
    public abstract List<Consulta> findByFechaYHoraBetween(LocalDateTime mañana, LocalDateTime tarde);
    public abstract List<Consulta> findByEspecialidadIdEspecialidad(Long idEspecialidad);
    public abstract List<Consulta> findByMedicoIdMedico(Long idMedico);
    public abstract List<Consulta> findByPagado(Boolean pagado);
    public abstract boolean existsByMedicoIdMedicoAndFechaYHoraAfter(Long idMedico, LocalDateTime fechaYHora);
    public abstract List<Consulta> findByFechaPagoAndPagadoIsTrue(LocalDate fecha);
}
