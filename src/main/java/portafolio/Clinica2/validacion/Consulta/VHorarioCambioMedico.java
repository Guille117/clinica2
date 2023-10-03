package portafolio.Clinica2.validacion.Consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import portafolio.Clinica2.dto.DtoConsultaModificar;
import portafolio.Clinica2.modelo.Consulta;
import portafolio.Clinica2.repositorio.IConsultaRepository;



@Component
public class VHorarioCambioMedico implements IValidarActualizarconsulta{
    // verificamos que en caso se cambie el médico de la consulta, este no tenga otra consulta programada

    @Autowired
    private IConsultaRepository cr;
    
    @Override
    public void validarActualizacion(DtoConsultaModificar dto) {
        if(dto.getIdMedico() != null){
            if(dto.getFechaYHora() != null){
                if(cr.existsByMedicoIdMedicoAndFechaYHora(dto.getIdMedico(), dto.getFechaYHora())){
                    throw new ValidationException("El médico ya cuenta con una consulta programada para este horario");
                }
            }else{
                Consulta con = cr.findById(dto.getIdConsulta()).orElseThrow(() -> new EntityNotFoundException("Consulta no encontrado"));
                Consulta con2 = cr.findByMedicoIdMedicoAndFechaYHora(dto.getIdMedico(), con.getFechaYHora());

                if(con.getIdConsulta() != con2.getIdConsulta()){
                    throw new ValidationException("El médico ya cuenta con una consulta programada para este horario");
                }
            }
            
        }
    }
    
}
