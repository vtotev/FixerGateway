package com.task.fixergateway.exception.handler;

import com.task.fixergateway.exception.ConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Object>handleConflictException(ConflictException exception) {
       return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

}
