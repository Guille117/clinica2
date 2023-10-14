package portafolio.Clinica2.controlador;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import portafolio.Clinica2.dto.DtoPaciente;
import portafolio.Clinica2.modelo.Paciente;
import portafolio.Clinica2.servicio.servicePaciente.IPacienteService;

@RestController
@RequestMapping("/paciente")
@PreAuthorize("hasRole('ADMIN')")
public class PacienteController {
    
    @Autowired
    private IPacienteService gs;

    @PostMapping
    public ResponseEntity<?> guardar(@Valid  @RequestBody Paciente p, UriComponentsBuilder ur){
        gs.Sa(p);
        URI url = ur.path("/paciente").buildAndExpand(p.getIdPaciente()).toUri();
        return ResponseEntity.created(url).body(p);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<Paciente> obtener1(@PathVariable Long id){
        return ResponseEntity.ok().body(gs.getOne(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<List<Paciente>> obtenerTodos(){
        return ResponseEntity.ok().body(gs.getAll());
    }

    @GetMapping("/mayores")
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<List<Paciente>> mayores(){
        return ResponseEntity.ok().body(gs.mayores());
    }

    @GetMapping("/menores")
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<List<Paciente>> menores(){
        return ResponseEntity.ok().body(gs.menores());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        gs.De(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Paciente> actualizar(@Valid @RequestBody DtoPaciente dtoPaciente){
        gs.Up(dtoPaciente);
        return ResponseEntity.ok().body(gs.getOne(dtoPaciente.getIdPaciente()));
    }
}
