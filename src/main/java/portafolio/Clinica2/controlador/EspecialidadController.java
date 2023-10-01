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
import portafolio.Clinica2.dto.DtoEspecialidad;
import portafolio.Clinica2.modelo.Especialidad;
import portafolio.Clinica2.servicio.IGenericService;

@RestController
@RequestMapping("/especialidad")
public class EspecialidadController {
    @Autowired
    private IGenericService<Especialidad, DtoEspecialidad> gs;

    @PostMapping
    public ResponseEntity guardar(@Valid @RequestBody Especialidad es, UriComponentsBuilder ur){
        gs.Sa(es);
        URI url = ur.path("/especialidad/{id}").buildAndExpand(es.getIdEspecialidad()).toUri();

        return ResponseEntity.created(url).body(es);
    }

     @GetMapping("/{id}")
    public ResponseEntity<Especialidad> obtener1(@PathVariable Long id){
        return ResponseEntity.ok().body(gs.getOne(id));
    }

    @GetMapping
    public ResponseEntity<List<Especialidad>> obtenerTodos(){
        return ResponseEntity.ok().body(gs.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
        gs.De(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity modificar(@Valid @RequestBody DtoEspecialidad esp){
        gs.Up(esp);
        return ResponseEntity.ok().body(gs.getOne(esp.getIdEspecialidad()));
    }
}
