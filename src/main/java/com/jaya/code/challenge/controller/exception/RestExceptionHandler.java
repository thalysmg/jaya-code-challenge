package com.jaya.code.challenge.controller.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity handleClientErrorException(HttpClientErrorException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage());
        String errorMsg = "";
        for (FieldError error: exception.getFieldErrors())
            errorMsg += "Field '" + error.getField() + "' " + error.getDefaultMessage() + "; ";
        return ResponseEntity.badRequest().body(errorMsg);
    }
}
