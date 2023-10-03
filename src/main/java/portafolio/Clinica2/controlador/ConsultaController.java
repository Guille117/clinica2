package portafolio.Clinica2.controlador;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import portafolio.Clinica2.dto.DTOconsulta;
import portafolio.Clinica2.dto.DtoConsultaModificar;
import portafolio.Clinica2.modelo.Consulta;
import portafolio.Clinica2.servicio.IConsultaService;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    
    @Autowired
    private IConsultaService gs;

    @PostMapping
    public ResponseEntity guardarConsulta(@Valid @RequestBody Consulta c, UriComponentsBuilder ur){
        gs.Sa(c);
        URI url = ur.path("/consulta/{id}").buildAndExpand(c.getIdConsulta()).toUri();
        return ResponseEntity.created(url).body(c);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOconsulta> obtener1(@PathVariable Long id){
        return ResponseEntity.ok().body(gs.getOne(id));
    }

    @GetMapping
    public ResponseEntity<List<DTOconsulta>> todos(){
        return ResponseEntity.ok().body(gs.getAll());
    }

    @GetMapping("/porMedico/{idMedico}")
    public ResponseEntity<List<DTOconsulta>> obtenerPorMedico(@PathVariable Long idMedico){
        return ResponseEntity.ok().body(gs.getAllMedico(idMedico));
    }

    @GetMapping("/porEspecialidad/{idEspecialidad}")
    public ResponseEntity<List<DTOconsulta>> obtenerPorEspecialidad(@PathVariable Long idEspecialidad){
        return ResponseEntity.ok().body(gs.getAllEspecialidad(idEspecialidad));
    }

    @GetMapping("/porFecha")
    public ResponseEntity<List<DTOconsulta>> obtenerPorFecha (@RequestParam String fecha){
        return ResponseEntity.ok().body(gs.getAllFecha(fecha));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
        gs.De(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity actualizar(@Valid @RequestBody DtoConsultaModificar dtoCon){
        gs.Up(dtoCon);
        return ResponseEntity.ok().body(gs.getOne(dtoCon.getIdConsulta()));
    }

}
