package com.otto.lab4.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends GlobalException {

    private static final String MESSAGE = "User with such name already exists";
    private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

    public UserAlreadyExistsException() {
        super(MESSAGE, STATUS);
    }

}
