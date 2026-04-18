package com.colaborativos_gestao_sistema_api.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DataErrorsValidation>> handleBadRequest(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(DataErrorsValidation::new).toList());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<DataErrorsSimple> handleResponseStatus(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(new DataErrorsSimple(ex.getReason()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<DataErrorsSimple> handleUserNotFound(UsernameNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DataErrorsSimple(ex.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<DataErrorsSimple> handleBadCredentials(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new DataErrorsSimple("Email ou Senha inválidos."));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<DataErrorsSimple> handleAuthenticationError(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new DataErrorsSimple(ex.getMessage()));
    }

    private record DataErrorsValidation(String field, String message) {
        public DataErrorsValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

    private record DataErrorsSimple(String message) {}

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<DataErrorsSimple> handleHttpReadableError(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(new DataErrorsSimple("Data de nascimento inválida."));
    }
}