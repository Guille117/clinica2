package portafolio.Clinica2.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import portafolio.Clinica2.dto.DtoMedicamento.DtoMedVendido2;
import portafolio.Clinica2.modelo.MedicamentoVendido;
import portafolio.Clinica2.servicio.serviceMedicamento.IMedicamentoVendidoService;

@RestController
@RequestMapping("/medicamentoVenta")
public class VentaMedicamentoController {
    @Autowired
    private IMedicamentoVendidoService nose;

    @PostMapping
    public ResponseEntity guardar(@Valid @RequestBody MedicamentoVendido mv){
        nose.Sa(mv);
        return ResponseEntity.ok().body(nose.getOneVenta(mv.getIdVentaMedicamento()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoMedVendido2> ob1(@PathVariable Long id){
        return ResponseEntity.ok().body(nose.getOneVenta(id));
    }

    @GetMapping
    public ResponseEntity<List<DtoMedVendido2>> todos(){
        return ResponseEntity.ok().body(nose.getAllVenta());
    }

    @GetMapping("/porFecha")
    public ResponseEntity<List<DtoMedVendido2>> obtenerPorFecha(@RequestParam String fecha){
        return ResponseEntity.ok().body(nose.obtenerPorFecha(fecha));
    }

    @GetMapping("/porMedicamento/{idMedicamento}")
    public ResponseEntity<List<DtoMedVendido2>> obtenerPorMedicamento(@PathVariable Long idMedicamento){
        return ResponseEntity.ok().body(nose.obtenerPorMedicamento(idMedicamento));
    }

    @GetMapping("/porCobro/{idCobro}")
    public ResponseEntity<List<DtoMedVendido2>> obtenerPorCobro(@PathVariable Long idCobro){
        return ResponseEntity.ok().body(nose.obtenerPorIdCobro(idCobro));
    }

}
