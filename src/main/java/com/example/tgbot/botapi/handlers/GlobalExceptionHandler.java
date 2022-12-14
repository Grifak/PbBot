package com.example.tgbot.botapi.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    private ResponseEntity<Object> handleNullPoint(NullPointerException ex){
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
