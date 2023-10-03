package portafolio.Clinica2.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import portafolio.Clinica2.modelo.Consulta;

@NoArgsConstructor
@Getter @Setter
public class DTOconsulta {
    private Long idConsulta;
    private Long idMedico;
    private String nomMedico;
    private Long idPaciente;
    private String nomPaciente;
    private String nomEspecialidad;
    private Double precioConsulta;
    private LocalDateTime fechaHora;
    private boolean pagado;


    public DTOconsulta(Consulta c){
        this.idConsulta = c.getIdConsulta();
        this.idMedico = c.getMedico().getIdMedico();
        this.nomMedico = c.getMedico().getP().nombreCompleto();
        this.idPaciente = c.getPaciente().getIdPaciente();
        this.nomPaciente = c.getPaciente().getP().nombreCompleto();
        this.nomEspecialidad = c.getEspecialidad().getNombre();
        this.precioConsulta = c.getEspecialidad().getPrecio();
        this.fechaHora = c.getFechaYHora();
        this.pagado = c.isPagado();
    }
}
