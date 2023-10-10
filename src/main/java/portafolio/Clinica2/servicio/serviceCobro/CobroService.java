package portafolio.Clinica2.servicio.serviceCobro;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import portafolio.Clinica2.Utilidades.UMedicamentosRepetidos;
import portafolio.Clinica2.dto.DtoCobro.DtoCobroIngreso;
import portafolio.Clinica2.dto.DtoCobro.DtoMostrarCobro;
import portafolio.Clinica2.dto.DtoMedicamento.DtoMedVendido1;
import portafolio.Clinica2.modelo.Cobro;
import portafolio.Clinica2.modelo.Consulta;
import portafolio.Clinica2.modelo.Medicamento;
import portafolio.Clinica2.modelo.MedicamentoVendido;
import portafolio.Clinica2.modelo.Paciente;
import portafolio.Clinica2.repositorio.ICobroRepository;
import portafolio.Clinica2.servicio.serviceConsulta.IConsultaService;
import portafolio.Clinica2.servicio.serviceMedicamento.IMedicamentoService;
import portafolio.Clinica2.servicio.serviceMedicamento.IMedicamentoVendidoService;
import portafolio.Clinica2.servicio.servicePaciente.IPacienteService;
import portafolio.Clinica2.validacion.Cobro.IValidCobro;
import portafolio.Clinica2.validacion.VentaMedicamento.IVVentaMedicamento;

@Service
public class CobroService implements ICobroService{

    @Autowired
    private ICobroRepository cr;
    @Autowired
    private IMedicamentoVendidoService mvs;
    @Autowired
    private IConsultaService servicioConsulta;
    @Autowired
    private IPacienteService servicioPaciente;
    @Autowired
    private IMedicamentoService medService;
    @Autowired
    private List<IValidCobro> validadoresCobro;
    @Autowired
    private List<IVVentaMedicamento> validadoresVentaMedicamento;

    @Override
    @Transactional
    public Long preRegistro(DtoCobroIngreso dtoCobro) {
        validadoresCobro.forEach(v -> v.validarCobro(dtoCobro));        // validamos que haya una consulta o una lista de medicamentos
        
        List<MedicamentoVendido> medicamentosPivote = null;
        
        Consulta consultaPivote = (dtoCobro.getConsulta() != null) ? 
        servicioConsulta.getOneOriginal(dtoCobro.getConsulta().getIdConsulta()) : null;
        
        Cobro cobroPivote = new Cobro();
        Double total = 0d;
        Integer cantidadMedicamentos = 0;
        
        // validamos los medicamentos
        if(dtoCobro.getMedicamentoVenta() != null){
            // unificamos los medicamentos que tengan el mismo id teniendo una lista sin medicamentos repetidos
            medicamentosPivote = UMedicamentosRepetidos.unificarMedicamentos(dtoCobro.getMedicamentoVenta());
            
            for(MedicamentoVendido m: medicamentosPivote){
                 // verificamos que tengamos medicamentos en farmacia
                validadoresVentaMedicamento.forEach(v -> v.validarVentaMedicamento(m));     
                cantidadMedicamentos += m.getCantidad();        // sumamos al contador
            }
        }

        this.Sa(cobroPivote);       // persistimos el primer cobro sin elementos, unicamente para generar el id;
        Long idGeneral = cobroPivote.getIdCobro();   

        if(medicamentosPivote != null){         // persistimos la lista de medicamentos
            total += mvs.guardarTodo(medicamentosPivote, cobroPivote);     // sumamos el total
            cobroPivote.setCantidadMedicamentos(cantidadMedicamentos);     // agregamos la cantidad de medicamentos
            cobroPivote.setIdPaciente(0L);      
            // seteamos el id del paciente presuponiedo que no tengamos consulta ni paciente, si tenemos se cambiará en los 
            //condicionales de abajo
        }

        if(consultaPivote != null){
            total += consultaPivote.getEspecialidad().getPrecio();       // sumamos el precio de la consulta
            servicioConsulta.pagarConsulta(consultaPivote, idGeneral);       // indicamos el pago de consulta
            cobroPivote.setConsulta(consultaPivote);        // agregamos la consulta al registro de venta
            cobroPivote.setNombrePaciente(consultaPivote.getPaciente().getP().nombreCompleto());      //agregamos nombre y apellido del paciente
            cobroPivote.setIdPaciente(consultaPivote.getPaciente().getIdPaciente());
        }

        if(consultaPivote == null && dtoCobro.getPaciente() != null){
            Paciente pacientePivote = servicioPaciente.getOne(dtoCobro.getPaciente().getIdPaciente());
            cobroPivote.setNombrePaciente(pacientePivote.getP().nombreCompleto());        // agregamos el nombre del paciente en caso no exista consulta
            cobroPivote.setIdPaciente(pacientePivote.getIdPaciente());
        }
        cobroPivote.setTotal(total);
        this.Sa(cobroPivote);
        return cobroPivote.getIdCobro();
    }

    @Override
    public void Sa(Cobro t) {
        cr.save(t);
    }  

    @Override
    public Cobro getOne(Long id) {
       return cr.findById(id).orElseThrow(() -> new EntityNotFoundException("Registro de cobro no encontrado"));
    }

    @Override
    public List<Cobro> getAll() {
        return cr.findAll();
    }

    @Override
    public DtoMostrarCobro mostrarCobroSimple(Long id) {
        return new DtoMostrarCobro(this.getOne(id), mvs.getAllCobroId(id));
    }

    @Override
    public List<DtoMostrarCobro> mostrarCobrosSimple() {
        List<Cobro> cobros = this.getAll();
        return convertirADtoMostrarCobro(cobros);
    }

    @Override
    public List<DtoMostrarCobro> mostrarPorFecha(String fech) {
        LocalDate fecha = LocalDate.parse(fech);
        List<Cobro> cobros = cr.findByFechaCobro(fecha);
        return convertirADtoMostrarCobro(cobros);
    }

    @Override
    public List<DtoMostrarCobro> mostrarPorPaciente(Long id) {
        List<Cobro> cobros = cr.findByIdPaciente(id);
        return convertirADtoMostrarCobro(cobros);
    }

    @Override
    public double mostrarTotalVentaPorDia(String fecha){
        List<DtoMostrarCobro> cobros = this.mostrarPorFecha(fecha);
        Double total = 0d;
        for(DtoMostrarCobro mc: cobros){
            total += mc.getTotal();
        }
        return total;
    }

    @Override
    @Transactional
    public void De(Long id) {
        Cobro cobro = this.getOne(id);
        
        if(LocalDate.now().isAfter(cobro.getFechaCobro().plusDays(5))){
            mvs.eliminarPorIdCobroGeneral(id);      // borramos medicamentos vendidos

            cr.deleteById(id);      // eliminamos el cobro

            servicioConsulta.De(cobro.getConsulta().getIdConsulta());         // eliminamos la consulta 
        }else{
            throw new ValidationException("Se podrá eliminar 5 dias después de la fecha de cobro");
        }
        
        
    }

    @Override
    @Transactional
    public void anularCobro(Long idCobro) {
       Cobro cobro = this.getOne(idCobro);

       if(LocalDate.now().isBefore(cobro.getFechaCobro().plusDays(4))){
            if(cobro.getCantidadMedicamentos() > 0){     // reestablecemos la cantidad de medicamentos si lo hubierá
                List<DtoMedVendido1> medVendidos =  mvs.getAllCobroId(idCobro);
                for(DtoMedVendido1 med: medVendidos){       
                Medicamento medicamento = medService.getOne(med.getIdMedicamento());    // obtenemos el medicamento
                medicamento.setCanDisponible(medicamento.getCanDisponible() + med.getCantidad());       // agregamos la cantidad vendida
                medService.Sa(medicamento);     // guardamos los cambios
            }
            // ya reestablecido todo, borramos los registros de la venta de medicamento por el id del cobro general 
            mvs.eliminarPorIdCobroGeneral(idCobro);     
            }

            if(cobro.getConsulta() != null){     // reestablecemos la consulta indicando que no se ha pagado
                Consulta consulta = servicioConsulta.getOneOriginal(cobro.getConsulta().getIdConsulta());
                consulta.setPagado(false);
                consulta.setFechaPago(null);
                //servicioConsulta.Sa(consulta);
            }

            cr.deleteById(idCobro);
        }else{
            throw new ValidationException("Se podrá anular un cobro unicamento en un plazo de 4 días");
        }
    }




    public List<DtoMostrarCobro> convertirADtoMostrarCobro(List<Cobro> cobros){
        List<DtoMostrarCobro> cobrosMostrar = new ArrayList<>();

        for(Cobro c: cobros){
            cobrosMostrar.add(new DtoMostrarCobro(c, mvs.getAllCobroId(c.getIdCobro())));
        }

        return cobrosMostrar;
    }
    
}
