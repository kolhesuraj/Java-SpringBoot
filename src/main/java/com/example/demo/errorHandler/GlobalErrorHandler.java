package com.example.demo.errorHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalErrorHandler {
    @ExceptionHandler(APIErrorHandler.class)
    public ResponseEntity<String> handleCustomErrorException(APIErrorHandler ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }
}
