package org.test.sse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SseControllerAdvice {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handler(IllegalStateException e) {

        return new ResponseEntity<String>(
                e.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }
}
