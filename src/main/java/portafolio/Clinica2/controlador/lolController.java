package portafolio.Clinica2.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import portafolio.Clinica2.dto.DtoMedicamentoVendido;
import portafolio.Clinica2.modelo.MedicamentoVendido;
import portafolio.Clinica2.servicio.IGenericService;

@RestController
@RequestMapping("/venta")
public class lolController {
    @Autowired
    private IGenericService<MedicamentoVendido, DtoMedicamentoVendido> nose;

    @PostMapping
    public ResponseEntity guardar(@Valid @RequestBody MedicamentoVendido mv){
        nose.Sa(mv);
        return ResponseEntity.ok().body(mv);
    }

    @GetMapping("/{id}")
    public ResponseEntity ob1(@PathVariable Long id){
        return ResponseEntity.ok().body(nose.getOne(id));
    }

    @GetMapping
    public ResponseEntity todos(){
        return ResponseEntity.ok().body(nose.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
        nose.De(id);
        return ResponseEntity.noContent().build();
    }

}
