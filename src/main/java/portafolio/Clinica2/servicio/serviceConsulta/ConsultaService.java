package portafolio.Clinica2.servicio.serviceConsulta;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import portafolio.Clinica2.dto.DtoRetornoTotales;
import portafolio.Clinica2.dto.DtoConsulta.DtoConsultaModificar;
import portafolio.Clinica2.dto.DtoConsulta.DtoConsultaMostrar;
import portafolio.Clinica2.dto.DtoConsulta.DtoConsultaParaCobro;
import portafolio.Clinica2.modelo.Consulta;
import portafolio.Clinica2.modelo.Especialidad;
import portafolio.Clinica2.modelo.Medico;
import portafolio.Clinica2.modelo.Paciente;
import portafolio.Clinica2.repositorio.IConsultaRepository;
import portafolio.Clinica2.repositorio.IEspecialidadRepository;
import portafolio.Clinica2.servicio.serviceMedico.IMedicoService;
import portafolio.Clinica2.servicio.servicePaciente.IPacienteService;
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

    @Override
    public void Sa(Consulta t) {
        LocalDateTime fechaModificada = t.getFechaYHora().withMinute(0).withSecond(0);
        t.setFechaYHora(fechaModificada);

        validadoresConsulta.forEach(v -> v.validar(t));

       cr.save(t);
    }

    @Override
    public DtoConsultaMostrar getOne(Long id) {
        return new DtoConsultaMostrar(cr.findById(id).orElseThrow(() -> new EntityNotFoundException("Consulta no encontrada")));
    }


    @Override
    public List<DtoConsultaMostrar> getAll() {
        
        List<Consulta> consultas = cr.findAll();
        List<DtoConsultaMostrar> consultasDto = consultas.stream().map(DtoConsultaMostrar::new).collect(Collectors.toList());
        
        return consultasDto;
    }

    @Override
    public Consulta getOneOriginal(Long id) {
       return cr.findById(id).orElseThrow(() -> new EntityNotFoundException("Consulta no encontrada"));
    }

    @Override
    public void De(Long id) {
        cr.deleteById(id);
    }

    @Override
    public void Up(DtoConsultaModificar dtoCon) {

        Consulta consulta = this.getOneOriginal(dtoCon.getIdConsulta());

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
        //validadoresConsulta.forEach(v -> v.validar(consulta));
        this.Sa(consulta);
    }

    @Override
    public List<DtoConsultaParaCobro> getAllMedico(Long idMedico) {     // consultas por médico
        List<Consulta> consultas1= cr.findByMedicoIdMedico(idMedico);
        List<DtoConsultaParaCobro> listDto1 = consultas1.stream().map(DtoConsultaParaCobro::new).collect(Collectors.toList());
        return listDto1;
    }

    @Override
    public List<DtoConsultaParaCobro> getAllEspecialidad(Long idEspecialidad) {     // consultas por especialidad
        List<Consulta> consultas2 = cr.findByEspecialidadIdEspecialidad(idEspecialidad);
        List<DtoConsultaParaCobro> listDto2 = consultas2.stream().map(DtoConsultaParaCobro::new).collect(Collectors.toList());
        return listDto2;
    }

    @Override
    public List<DtoConsultaParaCobro> getAllFecha(String fecha) {       // consultas por fecha
        LocalDateTime fech;
        try{
            fech = LocalDateTime.parse(fecha); 
        }catch(DateTimeParseException e){           // si recibimos recibimos un LocalDate lo convertimos a LocalDateTime para poder realizar la consulta
            StringBuilder pivote = new StringBuilder();
            pivote.append(fecha);
            pivote.append("T00:00");
            fech = LocalDateTime.parse(pivote);
        }
        

        LocalDateTime mañana = fech.withHour(7).withMinute(0).withSecond(0);
        LocalDateTime tarde = fech.withHour(17).withMinute(0).withSecond(0);

        List<Consulta> consultas3 = cr.findByFechaYHoraBetween(mañana, tarde);
        List<DtoConsultaParaCobro> listDto3 = consultas3.stream().map(DtoConsultaParaCobro::new).collect(Collectors.toList());

        return listDto3;

    }

    @Override
    public List<DtoConsultaParaCobro> getPagadoOnO(Boolean pagado) {
        List<Consulta> con = cr.findByPagado(pagado);
        List<DtoConsultaParaCobro> consultas = con.stream().map(DtoConsultaParaCobro::new).collect(Collectors.toList());
        return consultas;
    }

    @Override
    @Transactional
    public void pagarConsulta(Consulta c, Long idGeneral) {
        c.setPagado(true);
        c.setFechaPago(LocalDate.now());
    }

    @Override
    public DtoRetornoTotales totalCosultaPorFecha(String fecha) {
        List<Consulta> consultas = cr.findByFechaPagoAndPagadoIsTrue(LocalDate.parse(fecha));
        int cantidad = consultas.size();
        double total = 0d;

        for(Consulta con: consultas){
            total += con.getEspecialidad().getPrecio();    
        }

        return new DtoRetornoTotales("fecha", fecha, cantidad, total); 
    }
}
