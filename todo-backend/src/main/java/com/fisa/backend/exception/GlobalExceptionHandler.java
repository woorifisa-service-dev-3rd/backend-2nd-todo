package com.fisa.backend.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TodoException.class)
    public ResponseEntity<ErrorResponse> handleTodoException(
            TodoException exception) {
        return ResponseEntity.status(exception.getStatus())
                .body(new ErrorResponse(exception.getStatus().value(), exception.getMessage()));
    }
}
