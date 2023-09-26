package portafolio.Clinica2.controlador;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import portafolio.Clinica2.modelo.Medico;
import portafolio.Clinica2.servicio.IGenericService;

@RestController
@RequestMapping("/medico")
public class MedicoController {
    
    @Autowired
    private IGenericService<Medico> gs;

    @PostMapping
    public ResponseEntity guardar(@RequestBody @Valid Medico m, UriComponentsBuilder ur){
        gs.Sa(m);
        URI url = ur.path("/medico/{id}").buildAndExpand(m.getIdMedico()).toUri();
        return ResponseEntity.created(url).body(m);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> obtener1(@PathVariable Long id){
        return ResponseEntity.ok().body(gs.getOne(id));
    }

    @GetMapping
    public ResponseEntity<List<Medico>> obtenerTodos(){
        return ResponseEntity.ok().body(gs.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
        gs.De(id);
        return ResponseEntity.noContent().build();
    }

}
