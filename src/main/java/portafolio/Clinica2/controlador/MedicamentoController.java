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
import portafolio.Clinica2.dto.DtoMedicamento.DtoMedicamento;
import portafolio.Clinica2.modelo.Medicamento;
import portafolio.Clinica2.servicio.serviceMedicamento.IMedicamentoService;

@RestController
@RequestMapping("/medicamento")
@PreAuthorize("hasRole('ADMIN')")
public class MedicamentoController {
    @Autowired
    private IMedicamentoService gs;

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Medicamento m, UriComponentsBuilder ur){
        gs.Sa(m);
        URI url = ur.path("/medicamento/{id}").buildAndExpand(m.getIdMedicamento()).toUri();
        return ResponseEntity.created(url).body(m);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<Medicamento> obtener1(@PathVariable Long id){
        return ResponseEntity.ok().body(gs.getOne(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<List<Medicamento>> obtenerTodos(){
        return ResponseEntity.ok().body(gs.getAll());
    }

    @GetMapping("/marca")
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<List<Medicamento>> medFecha(@RequestParam String marca){
        return ResponseEntity.ok().body(gs.medicamentoPorMarca(marca));
    } 

    @GetMapping("/aCaducar")
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<List<Medicamento>> aCaducar(){
        return ResponseEntity.ok().body(gs.medicamentoACaducar());
    }

    @GetMapping("/caducado")
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<List<Medicamento>> caducados(){
        return ResponseEntity.ok().body(gs.medicamentoCaducado());
    }

    @GetMapping("/sinUnidades")
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<List<Medicamento>> sinUnidades(){
        return ResponseEntity.ok().body(gs.medicamentoSinExistencia());
    }

    @PutMapping
    public ResponseEntity<?> modificarMedicamento(@Valid @RequestBody DtoMedicamento dtoMed){
        gs.Up(dtoMed);
        return ResponseEntity.ok().body(gs.getOne(dtoMed.getIdMedicamento()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        gs.De(id);
        return ResponseEntity.noContent().build();
    }
}
