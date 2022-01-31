package com.softserve.itacademy.vkhomenko.serviceapp2.exception.handle;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public interface ErrorFactory {
    Error create(HttpStatus status, String message, Exception ex);
    Error create(HttpStatus status, Exception ex);
}
