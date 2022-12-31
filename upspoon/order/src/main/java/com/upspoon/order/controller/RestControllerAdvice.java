package com.upspoon.order.controller;

import com.upspoon.common.exceptions.BusinessTypeDoesNotRecognisedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author burak.yesildal
 */


@ControllerAdvice
public class RestControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @ExceptionHandler(BusinessTypeDoesNotRecognisedException.class)
    public ResponseEntity<Object> handleBusinessTypeDoesNotRecognised(
            BusinessTypeDoesNotRecognisedException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("errorMessage", ex.getMessage());
        return ResponseEntity.badRequest().body(body);
    }


}


