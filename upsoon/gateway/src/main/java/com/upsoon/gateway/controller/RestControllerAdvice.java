package com.upsoon.gateway.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.reflect.UndeclaredThrowableException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleInternalServiceError(
            Exception ex, WebRequest request) {

        String response = ((UndeclaredThrowableException) ex).getUndeclaredThrowable().getMessage();
        Map<String, String> body = Arrays.stream(response.replace("{", "").replace("}", "").split(","))
                .map(arrayData -> arrayData.split("="))
                .collect(Collectors.toMap(d -> ((String) d[0]).trim(), d -> (String) d[1]));


        body.put("timestamp", LocalDateTime.now().toString());
        return ResponseEntity.ok().body(body);
    }
}
