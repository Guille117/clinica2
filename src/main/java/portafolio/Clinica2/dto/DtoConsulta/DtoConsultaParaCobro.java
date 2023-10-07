package portafolio.Clinica2.dto.DtoConsulta;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import portafolio.Clinica2.modelo.Consulta;

@NoArgsConstructor
@Getter @Setter
public class DtoConsultaParaCobro {
    private Long idConsulta;
    private Long idMedico;
    private String nomMedico;
    //private Long idPaciente;
    //private String nomPaciente;
    private String nomEspecialidad;
    private Double precioConsulta;

    public DtoConsultaParaCobro(Consulta c){
        this.idConsulta = c.getIdConsulta();
        this.idMedico = c.getMedico().getIdMedico();
        this.nomMedico = c.getMedico().getP().nombreCompleto();
        //this.idPaciente = c.getPaciente().getIdPaciente();
        //this.nomPaciente = c.getPaciente().getP().nombreCompleto();
        this.nomEspecialidad = c.getEspecialidad().getNombre();
        this.precioConsulta = c.getEspecialidad().getPrecio();
    
    }
}
