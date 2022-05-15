package br.com.cursoudemy.productapi.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerGlobalAdvice {

    @ExceptionHandler(ValidateRequestException.class)
    public ResponseEntity<ExceptionDetails> handleValidateRequestException(ValidateRequestException ex) {
        var exceptionDetails = new ExceptionDetails();
        exceptionDetails.setMessage(ex.getMessage());
        exceptionDetails.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionDetails.setMoment(Instant.now());
        exceptionDetails.setCause(ex.getLocalizedMessage());
        return ResponseEntity.badRequest().body(exceptionDetails);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionDetails> handleValidateRequestException(AuthenticationException ex) {
        var exceptionDetails = new ExceptionDetails();
        exceptionDetails.setMessage(ex.getMessage());
        exceptionDetails.setStatus(HttpStatus.UNAUTHORIZED.value());
        exceptionDetails.setMoment(Instant.now());
        exceptionDetails.setCause(ex.getStackTrace().getClass().getName());
        return ResponseEntity.badRequest().body(exceptionDetails);
    }
}
