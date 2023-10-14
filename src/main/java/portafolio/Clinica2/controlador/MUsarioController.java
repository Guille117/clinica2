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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import portafolio.Clinica2.modelo.MUsuario;
import portafolio.Clinica2.servicio.IGenericService;

@RestController
@RequestMapping("/usuario")
@PreAuthorize("hasRole ('ADMIN')")
public class MUsarioController {
    @Autowired
    private IGenericService<MUsuario> mus;

    @PostMapping
    public ResponseEntity<?> guardarUsuario(@RequestBody @Valid MUsuario usuario, UriComponentsBuilder ur){
        URI url = ur.path("/usuario/{id}").buildAndExpand(usuario.getIdUsuario()).toUri();
        mus.Sa(usuario);
        return ResponseEntity.created(url).body(usuario);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<MUsuario> obtener1(@PathVariable Long idUsuario){
        return ResponseEntity.ok().body(mus.getOne(idUsuario));
    }

    @GetMapping
    public ResponseEntity<List<MUsuario>> obtenerTodos(){
        return ResponseEntity.ok().body(mus.getAll());
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long idUsuario){
        mus.De(idUsuario);
        return ResponseEntity.noContent().build();
    }
}
