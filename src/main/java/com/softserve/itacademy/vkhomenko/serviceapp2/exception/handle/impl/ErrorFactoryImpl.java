package com.softserve.itacademy.vkhomenko.serviceapp2.exception.handle.impl;

import com.softserve.itacademy.vkhomenko.serviceapp2.exception.handle.Error;
import com.softserve.itacademy.vkhomenko.serviceapp2.exception.handle.ErrorFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ErrorFactoryImpl implements ErrorFactory {
    @Override
    public Error create(HttpStatus status, String message, Exception ex) {
        return ErrorImpl.of(status, message, ex);
    }

    @Override
    public Error create(HttpStatus status, Exception ex) {
        return ErrorImpl.of(status, ex);
    }
}
