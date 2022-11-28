package com.otto.lab4.exception;

import org.springframework.http.HttpStatus;

public class InvalidRefreshTokenException extends GlobalException {

    private static final String MESSAGE = "Invalid refresh token";
    private static final HttpStatus STATUS = HttpStatus.FORBIDDEN;

    public InvalidRefreshTokenException() {
        super(MESSAGE, STATUS);
    }
}
