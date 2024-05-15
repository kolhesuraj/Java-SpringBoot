package com.example.demo.error_handler;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalErrorHandler {

    /**
     * @param ex - Exception happen in application
     * @return Response
     */
    @ExceptionHandler(value = {Exception.class, APIErrorHandler.class})
    public @NotNull ResponseEntity<Map<String, String>> handleAPIError(Exception ex) {
        HttpHeaders headers = new HttpHeaders();
        Map<String, String> errorResponse = new HashMap<>();
        String message = ex.getMessage() != null ? ex.getMessage() : "Internal Server Error";
        errorResponse.put("message", message);

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (ex instanceof APIErrorHandler) {
            status = ((APIErrorHandler) ex).getStatus();
        }
        errorResponse.put("status code", String.valueOf(status));

        return new ResponseEntity<>(errorResponse,headers, status);

    }

}
