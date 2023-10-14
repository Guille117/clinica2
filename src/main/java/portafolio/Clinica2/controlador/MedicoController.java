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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import portafolio.Clinica2.dto.DtoMedico;
import portafolio.Clinica2.modelo.Medico;
import portafolio.Clinica2.servicio.serviceMedico.IMedicoService;

@RestController
@RequestMapping("/medico")
@PreAuthorize("hasRole('ADMIN')")
public class MedicoController {
    
    @Autowired
    private IMedicoService gs;

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody @Valid Medico m, UriComponentsBuilder ur){
        gs.Sa(m);
        URI url = ur.path("/medico/{id}").buildAndExpand(m.getIdMedico()).toUri();
        return ResponseEntity.created(url).body(m);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<Medico> obtener1(@PathVariable Long id){
        return ResponseEntity.ok().body(gs.getOne(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<List<Medico>> obtenerTodos(){
        return ResponseEntity.ok().body(gs.getAll());
    }

    @GetMapping("/estado")
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<?> medActivos(@RequestParam Boolean estado){
        return ResponseEntity.ok().body(gs.activos(estado));
    }

    @GetMapping("/especialidad")
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<?> medEspecialidad(@RequestParam String nombre){
        return ResponseEntity.ok().body(gs.medEspecialidad(nombre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        gs.De(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<?> modificar(@Valid @RequestBody DtoMedico meDto){
        gs.Up(meDto);
        return ResponseEntity.ok().body(gs.getOne(meDto.getIdMedico()));
    }

    @PutMapping("/activar/{id}")
    public ResponseEntity<?> activarMedico(@PathVariable Long id){
        gs.activarMedico(id);
        return ResponseEntity.ok().body(gs.getOne(id));
    }

    @PutMapping("/desactivar/{id}")
    public ResponseEntity<?> desactivarMedico(@PathVariable Long id){
        gs.desactivarMedico(id);
        return ResponseEntity.ok().body(gs.getOne(id));
    }
}
