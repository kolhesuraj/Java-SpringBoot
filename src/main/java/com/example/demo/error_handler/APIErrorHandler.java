package com.example.demo.error_handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class APIErrorHandler extends RuntimeException {

    private final HttpStatus status;

    public APIErrorHandler(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
    public APIErrorHandler(String message) {
        super(message);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }


}
