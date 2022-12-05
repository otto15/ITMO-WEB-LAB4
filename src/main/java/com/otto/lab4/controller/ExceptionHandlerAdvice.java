package com.otto.lab4.controller;

import com.otto.lab4.controller.dto.Message;
import com.otto.lab4.exception.GlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler
    public ResponseEntity<Message> handle(GlobalException e) {
        return new ResponseEntity<>(new Message(e.getMessage()), e.getStatus());
    }

    @ExceptionHandler
    ResponseEntity<Message> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>(new Message("Validation error: " + e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    ResponseEntity<Message> handleConstraintViolationException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> errors = bindingResult
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField,
                        fieldError -> fieldError.getDefaultMessage() == null ?
                                "Validation failed!" :
                                fieldError.getDefaultMessage()));
        Message error = new Message(errors.toString());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
