package com.fldsmdfr.domainSecurity.exceptions.role;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionResponseAppRoleHandler {
    @ExceptionHandler(AppRoleNotFoundException.class)
    public ResponseEntity<HashMap<String, Object>> handleRoleNotFoundException(AppRoleNotFoundException ex, WebRequest request) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Not Found");
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(AppRoleConflictException.class)
    public ResponseEntity<HashMap<String, Object>> handleRoleConflictException(AppRoleConflictException ex, WebRequest request) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.CONFLICT.value());
        body.put("error", "Conflict");
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<HashMap<String, Object>> handleAllExceptions(Exception ex, WebRequest request) {
    //     HashMap<String, Object> body = new HashMap<>();
    //     body.put("timestamp", LocalDateTime.now());
    //     body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
    //     body.put("error", "Internal Server Error");
    //     body.put("message", ex.getMessage());
    //     body.put("path", request.getDescription(false).replace("uri=", ""));

    //     return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    // }
}
