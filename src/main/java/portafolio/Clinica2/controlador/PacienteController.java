package portafolio.Clinica2.controlador;

import java.net.URI;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import portafolio.Clinica2.modelo.Medicamento;
import portafolio.Clinica2.modelo.Paciente;
import portafolio.Clinica2.servicio.IGenericService;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    
    @Autowired
    private IGenericService<Paciente> gs;

    @PostMapping
    public ResponseEntity guardar(@Valid  @RequestBody Paciente p, UriComponentsBuilder ur){
        gs.Sa(p);
        URI url = ur.path("/paciente").buildAndExpand(p.getIdPaciente()).toUri();
        return ResponseEntity.created(url).body(p);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> obtener1(@PathVariable Long id){
        return ResponseEntity.ok().body(gs.getOne(id));
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> obtenerTodos(){
        return ResponseEntity.ok().body(gs.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
        gs.De(id);
        return ResponseEntity.noContent().build();
    }
}
