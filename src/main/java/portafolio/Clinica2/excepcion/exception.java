package portafolio.Clinica2.excepcion;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;

@RestControllerAdvice
public class exception {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity noEncontrado(EntityNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity errorValidacion(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(Exc::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorValidacion2(ValidationException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity duplicados(SQLIntegrityConstraintViolationException e){
        return ResponseEntity.unprocessableEntity().body(e.getMessage());
    }

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)     // enum incorrecto
    public ResponseEntity enumIncorrecto(){
        return ResponseEntity.badRequest().body("No existe la especialidad seleccionada.");
    } 


    private record Exc(String campo, String error) {
        public Exc(FieldError er){
            this(er.getField(), er.getDefaultMessage());
        }

    }

}
