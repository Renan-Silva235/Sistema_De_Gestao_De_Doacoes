package com.colaborativos_gestao_sistema_api.Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DataErrorsValidation>> tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DataErrorsValidation::new).toList());
    }

    private record DataErrorsValidation(String field, String message) {
        public DataErrorsValidation(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<DataErrorsSimple> trustErrors(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(new DataErrorsSimple(ex.getReason()));
    }

    private record DataErrorsSimple(String message) {}
}