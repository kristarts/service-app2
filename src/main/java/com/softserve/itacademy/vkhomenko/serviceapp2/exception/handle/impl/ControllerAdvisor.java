package com.softserve.itacademy.vkhomenko.serviceapp2.exception.handle.impl;

import com.softserve.itacademy.vkhomenko.serviceapp2.exception.ActionNotAllowedException;
import com.softserve.itacademy.vkhomenko.serviceapp2.exception.InvalidUuidException;
import com.softserve.itacademy.vkhomenko.serviceapp2.exception.NotFoundException;
import com.softserve.itacademy.vkhomenko.serviceapp2.exception.RegistrationException;
import com.softserve.itacademy.vkhomenko.serviceapp2.exception.handle.Error;
import com.softserve.itacademy.vkhomenko.serviceapp2.exception.handle.ErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @Autowired
    private ErrorFactory errorFactory;

    private static ResponseEntity<Object> responseEntity(Error error) {
        return new ResponseEntity<>(error, error.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        String errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));

        return responseEntity(errorFactory.create(HttpStatus.BAD_REQUEST, errors, ex));
    }

    @ExceptionHandler({InvalidUuidException.class})
    public ResponseEntity<Object> HandleException(InvalidUuidException ex, WebRequest request) {
        return responseEntity(errorFactory.create(HttpStatus.BAD_REQUEST, ex.getMessage(), ex));
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> HandleException(ConstraintViolationException ex, WebRequest request) {
        return responseEntity(errorFactory.create(HttpStatus.BAD_REQUEST, ex.getMessage(), ex));
    }

    @ExceptionHandler({ActionNotAllowedException.class})
    public ResponseEntity<Object> HandleException(ActionNotAllowedException ex, WebRequest request) {
        return responseEntity(errorFactory.create(HttpStatus.FORBIDDEN, ex.getMessage(), ex));
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> HandleException(NotFoundException ex, WebRequest request) {
        return responseEntity(errorFactory.create(HttpStatus.NOT_FOUND, ex.getMessage(), ex));
    }

    @ExceptionHandler({RegistrationException.class})
    public ResponseEntity<Object> HandleException(RegistrationException ex, WebRequest request) {
        return responseEntity(errorFactory.create(HttpStatus.BAD_REQUEST, ex.getMessage(), ex));
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<Object> HandleException(AuthenticationException ex, WebRequest request) {
        return responseEntity(errorFactory.create(HttpStatus.UNAUTHORIZED, ex.getMessage(), ex));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> HandleException(Exception ex, WebRequest request) {
        return responseEntity(errorFactory.create(HttpStatus.INTERNAL_SERVER_ERROR, ex));
    }
}
