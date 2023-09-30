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
import portafolio.Clinica2.dto.DtoMedicamento;
import portafolio.Clinica2.modelo.Medicamento;
import portafolio.Clinica2.servicio.IGenericService;

@RestController
@RequestMapping("/medicamento")
public class MedicamentoController {
    @Autowired
    private IGenericService<Medicamento, DtoMedicamento> gs;

    @PostMapping
    public ResponseEntity guardar(@Valid @RequestBody Medicamento m, UriComponentsBuilder ur){
        gs.Sa(m);
        URI url = ur.path("/medicamento/{id}").buildAndExpand(m.getIdMedicamento()).toUri();
        return ResponseEntity.created(url).body(m);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicamento> obtener1(@PathVariable Long id){
        return ResponseEntity.ok().body(gs.getOne(id));
    }

    @GetMapping
    public ResponseEntity<List<Medicamento>> obtenerTodos(){
        return ResponseEntity.ok().body(gs.getAll());
    }

    @PutMapping
    public ResponseEntity modificarMedicamento(@Valid @RequestBody DtoMedicamento dtoMed){
        gs.Up(dtoMed);
        return ResponseEntity.ok().body(gs.getOne(dtoMed.getIdMedicamento()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
        gs.De(id);
        return ResponseEntity.noContent().build();
    }
}
