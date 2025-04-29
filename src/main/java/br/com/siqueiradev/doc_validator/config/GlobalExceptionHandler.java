package br.com.siqueiradev.doc_validator.config;


import br.com.siqueiradev.doc_validator.adapter.dto.response.ResponseErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ResponseErrorDTO> handleResponseStatusException(ResponseStatusException ex) {
        ResponseErrorDTO error = new ResponseErrorDTO(
                ex.getReason(),
                ex.getStatusCode().value(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(ex.getStatusCode()).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseErrorDTO> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ResponseErrorDTO error = new ResponseErrorDTO(
                "Erro de validação",
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                errors
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseErrorDTO> handleGenericException(Exception ex) {
        ResponseErrorDTO error = new ResponseErrorDTO(
                "Erro interno. Contate o suporte.",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseErrorDTO> handleIllegalArgumentException(IllegalArgumentException ex) {
        ResponseErrorDTO error = new ResponseErrorDTO(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
