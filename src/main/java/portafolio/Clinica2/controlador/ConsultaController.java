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
import portafolio.Clinica2.dto.DtoRetornoTotales;
import portafolio.Clinica2.dto.DtoConsulta.DtoConsultaModificar;
import portafolio.Clinica2.dto.DtoConsulta.DtoConsultaMostrar;
import portafolio.Clinica2.dto.DtoConsulta.DtoConsultaParaCobro;
import portafolio.Clinica2.modelo.Consulta;
import portafolio.Clinica2.servicio.serviceConsulta.IConsultaService;

@RestController
@RequestMapping("/consulta")
@PreAuthorize("hasRole ('ADMIN')")
public class ConsultaController {
    
    @Autowired
    private IConsultaService gs;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<Consulta> guardarConsulta(@Valid @RequestBody Consulta c, UriComponentsBuilder ur){
        gs.Sa(c);
        URI url = ur.path("/consulta/{id}").buildAndExpand(c.getIdConsulta()).toUri();
        return ResponseEntity.created(url).body(c);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<DtoConsultaMostrar> obtener1(@PathVariable Long id){
        return ResponseEntity.ok().body(gs.getOne(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<List<DtoConsultaMostrar>> todos(){
        return ResponseEntity.ok().body(gs.getAll());
    }

    @GetMapping("/porMedico/{idMedico}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<List<DtoConsultaParaCobro>> obtenerPorMedico(@PathVariable Long idMedico){
        return ResponseEntity.ok().body(gs.getAllMedico(idMedico));
    }

    @GetMapping("/porEspecialidad/{idEspecialidad}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<List<DtoConsultaParaCobro>> obtenerPorEspecialidad(@PathVariable Long idEspecialidad){
        return ResponseEntity.ok().body(gs.getAllEspecialidad(idEspecialidad));
    }

    @GetMapping("/porFecha")
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<List<DtoConsultaParaCobro>> obtenerPorFecha (@RequestParam String fecha){
        return ResponseEntity.ok().body(gs.getAllFecha(fecha));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        gs.De(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pagado/{pagado}")
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    public ResponseEntity<List<DtoConsultaParaCobro>> consultasPagadas(@PathVariable Boolean pagado){
        return ResponseEntity.ok().body(gs.getPagadoOnO(pagado));
    }

    @GetMapping("/totalPorFecha")
    public ResponseEntity<DtoRetornoTotales> totalPorFecha(@RequestParam String fecha){
        return ResponseEntity.ok().body(gs.totalCosultaPorFecha(fecha));

    }


    @PutMapping
    public ResponseEntity<?> actualizar(@Valid @RequestBody DtoConsultaModificar dtoCon){
        gs.Up(dtoCon);
        return ResponseEntity.ok().body(gs.getOne(dtoCon.getIdConsulta()));
    }
}
