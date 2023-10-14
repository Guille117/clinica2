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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import portafolio.Clinica2.dto.DtoCobro.DtoCobroIngreso;
import portafolio.Clinica2.dto.DtoCobro.DtoMostrarCobro;
import portafolio.Clinica2.servicio.serviceCobro.ICobroService;

@RestController
@RequestMapping("/cobro")
    @PreAuthorize("hasRole('ADMIN')")
public class CobroController {
    
    @Autowired
    private ICobroService cs;

    @PostMapping
    public ResponseEntity<DtoMostrarCobro> guardar(@RequestBody @Valid DtoCobroIngreso cobro, UriComponentsBuilder ur){
        Long id = cs.preRegistro(cobro);
        URI url = ur.path("/cobro/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(url).body(cs.mostrarCobroSimple(id));
    }

    @GetMapping("/{idCobro}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<DtoMostrarCobro> obtener1(@PathVariable Long idCobro){
        return ResponseEntity.ok().body(cs.mostrarCobroSimple(idCobro));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<List<DtoMostrarCobro>> obtenerTodos(){
        return ResponseEntity.ok().body(cs.mostrarCobrosSimple());
    }

    @GetMapping("/porFecha")
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<List<DtoMostrarCobro>> obtenerPorFeccha(@RequestParam String fecha){
        return ResponseEntity.ok().body(cs.mostrarPorFecha(fecha));
    }

    @GetMapping("/porPaciente/{idPaciente}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<List<DtoMostrarCobro>> obtenerPorPaciente(@PathVariable Long idPaciente){
        return ResponseEntity.ok().body(cs.mostrarPorPaciente(idPaciente));
    }

    @GetMapping("/totalPorFecha")
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<String> totalPorFecha(@RequestParam String fecha){
        return ResponseEntity.ok().body("Durante la fecha " +  fecha 
                    + " se realizó un total de cobros de: " + cs.mostrarTotalVentaPorDia(fecha));
    }

    @DeleteMapping("/{idCobro}")
    public ResponseEntity<?> eliminar(@PathVariable Long idCobro){
        cs.De(idCobro);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/anular/{idCobro}")
    public ResponseEntity<?> anularConsulta(@PathVariable long idCobro){
        cs.anularCobro(idCobro);
           return ResponseEntity.noContent().build();
    }
    

}
