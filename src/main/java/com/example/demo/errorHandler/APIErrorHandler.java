package com.example.demo.errorHandler;

import org.springframework.http.HttpStatus;

public class APIErrorHandler extends RuntimeException {

    private final HttpStatus status;

    public APIErrorHandler(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
