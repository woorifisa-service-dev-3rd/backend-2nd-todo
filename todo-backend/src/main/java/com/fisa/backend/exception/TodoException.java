package com.fisa.backend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TodoException extends RuntimeException{

    private HttpStatus status;

    public TodoException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

}
