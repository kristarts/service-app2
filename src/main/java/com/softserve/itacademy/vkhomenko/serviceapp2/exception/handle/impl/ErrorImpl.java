package com.softserve.itacademy.vkhomenko.serviceapp2.exception.handle.impl;

import com.softserve.itacademy.vkhomenko.serviceapp2.exception.handle.BasicError;
import com.softserve.itacademy.vkhomenko.serviceapp2.exception.handle.Error;
import org.springframework.http.HttpStatus;

public class ErrorImpl extends BasicError {

    public ErrorImpl(HttpStatus status) { super(status); }

    public ErrorImpl(HttpStatus status, String message) { super(status, message); }

    public static Error of(HttpStatus status, String message, Exception ex) {
        return new ErrorImpl(status, message);
    }

    public static Error of(HttpStatus status, Exception ex) {
        return new ErrorImpl(status);
    }
}
