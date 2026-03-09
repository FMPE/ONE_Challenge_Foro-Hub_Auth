package foro.hub.api.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleBadRequest(MethodArgumentNotValidException e) {
        var errors = e.getFieldErrors().stream().map(DataErrorValidation::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    private record DataErrorValidation(String campo, String error) {
        public DataErrorValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
