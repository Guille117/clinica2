package portafolio.Clinica2.dto.DtoConsulta;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import portafolio.Clinica2.modelo.Consulta;

@NoArgsConstructor
@Getter @Setter
public class DtoConsultaMostrar extends DtoConsultaParaCobro{
    private Long idPaciente;
    private String nomPaciente;
    private LocalDateTime fechaYHora;
    private Boolean pagado;
    private LocalDate fechaPago;

    public DtoConsultaMostrar(Consulta c){
        super(c);
        this.idPaciente = c.getPaciente().getIdPaciente();
        this.nomPaciente = c.getPaciente().getP().nombreCompleto();
        this.fechaYHora = c.getFechaYHora();
        this.pagado = c.isPagado();
        this.fechaPago = c.getFechaPago();
    
    }
}

