package com.jaya.code.challenge.controller.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity handleClientErrorException(HttpClientErrorException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
