package portafolio.Clinica2.servicio;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import portafolio.Clinica2.dto.DTOconsulta;
import portafolio.Clinica2.dto.DtoConsultaModificar;
import portafolio.Clinica2.modelo.Consulta;
import portafolio.Clinica2.modelo.Especialidad;
import portafolio.Clinica2.modelo.Medico;
import portafolio.Clinica2.modelo.Paciente;
import portafolio.Clinica2.repositorio.IConsultaRepository;
import portafolio.Clinica2.repositorio.IEspecialidadRepository;
import portafolio.Clinica2.validacion.Consulta.IValidarActualizarconsulta;
import portafolio.Clinica2.validacion.Consulta.IValidarConsulta;

@Service
public class ConsultaService implements IConsultaService{
    
    @Autowired
    private IConsultaRepository cr;
    @Autowired
    private IMedicoService medicoService;
    @Autowired
    private IPacienteService pacienteService;
    @Autowired
    private IEspecialidadRepository especialidadRepo;
    @Autowired
    private List<IValidarConsulta> validadoresConsulta;
    @Autowired
    private List<IValidarActualizarconsulta> validActualizarConsu;

    @Override
    public void Sa(Consulta t) {
        LocalDateTime fechaModificada = t.getFechaYHora().withMinute(0).withSecond(0);
        t.setFechaYHora(fechaModificada);

        validadoresConsulta.forEach(v -> v.validar(t, "post"));

       cr.save(t);
    }

    @Override
    public DTOconsulta getOne(Long id) {
        return new DTOconsulta(cr.findById(id).orElseThrow(() -> new EntityNotFoundException("Consulta no encontrada")));
    }


    @Override
    public List<DTOconsulta> getAll() {
        
        List<Consulta> consultas = cr.findAll();
        List<DTOconsulta> consultasDto = consultas.stream().map(DTOconsulta::new).collect(Collectors.toList());
        
        return consultasDto;
    }

    @Override
    public Consulta getOneOriginal(Long id) {
       return cr.findById(id).orElseThrow(() -> new EntityNotFoundException("Consulta no encontrada"));
    }

    @Override
    public void De(Long id) {
        Consulta con = this.getOneOriginal(id);
        validadoresConsulta.forEach(v -> v.validar(con, "delete"));
        cr.deleteById(id);
    }

    @Override
    @Transactional
    public void Up(DtoConsultaModificar dtoCon) {
        validActualizarConsu.forEach(v -> v.validarActualizacion(dtoCon));

        Consulta consulta = cr.findById(dtoCon.getIdConsulta()).orElseThrow(() -> new EntityNotFoundException("Consulta no encontrada"));

        if(dtoCon.getIdMedico() != null){
            Medico med = medicoService.getOne(dtoCon.getIdMedico());
            consulta.setMedico(med);
        }if(dtoCon.getIdPaciente() != null){
            Paciente paciente = pacienteService.getOne(dtoCon.getIdPaciente());
            consulta.setPaciente(paciente);
        }
        if(dtoCon.getIdEspecialidad() != null){
            Especialidad especialidad = especialidadRepo.findById(dtoCon.getIdEspecialidad()).orElseThrow(() -> new EntityNotFoundException("Especialidad no encontrada"));
            consulta.setEspecialidad(especialidad);
        }
        if(dtoCon.getFechaYHora() != null){
            LocalDateTime fecha = dtoCon.getFechaYHora().withMinute(0).withSecond(0);
            consulta.setFechaYHora(fecha);
        }

        validadoresConsulta.forEach(v -> v.validar(consulta, "put"));
        
        cr.save(consulta);
    }

    @Override
    public List<DTOconsulta> getAllMedico(Long idMedico) {
        List<Consulta> consultas1= cr.findByMedicoIdMedico(idMedico);
        List<DTOconsulta> listDto1 = consultas1.stream().map(DTOconsulta::new).collect(Collectors.toList());
        return listDto1;
    }

    @Override
    public List<DTOconsulta> getAllEspecialidad(Long idEspecialidad) {
        List<Consulta> consultas2 = cr.findByEspecialidadIdEspecialidad(idEspecialidad);
        List<DTOconsulta> listDto2 = consultas2.stream().map(DTOconsulta::new).collect(Collectors.toList());
        return listDto2;
    }

    @Override
    public List<DTOconsulta> getAllFecha(String fecha) {
        LocalDateTime fech;
        try{
            fech = LocalDateTime.parse(fecha); 
        }catch(DateTimeParseException e){
            StringBuilder pivote = new StringBuilder();
            pivote.append(fecha);
            pivote.append("T00:00");
            fech = LocalDateTime.parse(pivote);
        }
        

        LocalDateTime mañana = fech.withHour(7).withMinute(0).withSecond(0);
        LocalDateTime tarde = fech.withHour(17).withMinute(0).withSecond(0);

        List<Consulta> consultas3 = cr.findByFechaYHoraBetween(mañana, tarde);
        List<DTOconsulta> listDto3 = consultas3.stream().map(DTOconsulta::new).collect(Collectors.toList());

        return listDto3;

    }
}
