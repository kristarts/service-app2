package com.softserve.itacademy.vkhomenko.serviceapp2.exception.handle;

import org.springframework.http.HttpStatus;

public interface Error {
    HttpStatus getStatus();
    String getMessage();
}
