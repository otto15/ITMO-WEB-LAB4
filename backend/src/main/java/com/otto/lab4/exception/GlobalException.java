package com.otto.lab4.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GlobalException extends RuntimeException {

    private final HttpStatus status;

    public GlobalException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
