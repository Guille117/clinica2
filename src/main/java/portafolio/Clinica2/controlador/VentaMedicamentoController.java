package portafolio.Clinica2.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import portafolio.Clinica2.dto.DtoRetornoTotales;
import portafolio.Clinica2.dto.DtoMedicamento.DtoMedVendido2;
import portafolio.Clinica2.servicio.serviceMedicamento.IMedicamentoVendidoService;

@RestController
@RequestMapping("/medicamentoVenta")
@PreAuthorize("hasRole('ADMIN')")
public class VentaMedicamentoController {
    @Autowired
    private IMedicamentoVendidoService nose;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<DtoMedVendido2> ob1(@PathVariable Long id){
        return ResponseEntity.ok().body(nose.getOneVenta(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<List<DtoMedVendido2>> todos(){
        return ResponseEntity.ok().body(nose.getAllVenta());
    }

    @GetMapping("/porFecha")
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<List<DtoMedVendido2>> obtenerPorFecha(@RequestParam String fecha){
        return ResponseEntity.ok().body(nose.obtenerPorFecha(fecha));
    }

    @GetMapping("/porMedicamento/{idMedicamento}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<List<DtoMedVendido2>> obtenerPorMedicamento(@PathVariable Long idMedicamento){
        return ResponseEntity.ok().body(nose.obtenerPorMedicamento(idMedicamento));
    }

    @GetMapping("/porCobro/{idCobro}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<List<DtoMedVendido2>> obtenerPorCobro(@PathVariable Long idCobro){
        return ResponseEntity.ok().body(nose.obtenerPorIdCobro(idCobro));
    }

    @GetMapping("/totalPorFecha")
    public ResponseEntity<DtoRetornoTotales> totalPorFecha(@RequestParam String fecha){
        return ResponseEntity.ok().body(nose.totalPorFecha(fecha));
    }

    @GetMapping("/totalPorMedicamento/{idMedicamento}")
    public ResponseEntity<DtoRetornoTotales> totalPorMed(@PathVariable Long idMedicamento){
        return ResponseEntity.ok().body(nose.totalPorMedicamento(idMedicamento));
    }

    @GetMapping("/totalPorIdCobro/{idCobro}")
    public ResponseEntity<DtoRetornoTotales> totalPorCobro(@PathVariable Long idCobro){
        return ResponseEntity.ok().body(nose.totalPorIdCobro(idCobro));
    }

}
