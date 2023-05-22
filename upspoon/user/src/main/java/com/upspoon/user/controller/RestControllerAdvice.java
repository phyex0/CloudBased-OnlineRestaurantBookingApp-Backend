package com.upspoon.user.controller;

import com.upspoon.common.exceptions.UserAlreadyExistException;
import com.upspoon.common.exceptions.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
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
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(
            UserNotFoundException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("errorMessage", "User not found exception!");
        return ResponseEntity.badRequest().body(body);
    }


    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Object> handleAlreadyExistException(
            UserAlreadyExistException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("errorMessage", "User already exist exception!");
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleUserCreateFailedException(
            RuntimeException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("errorMessage", "User could not created!");
        return ResponseEntity.badRequest().body(body);
    }
}


