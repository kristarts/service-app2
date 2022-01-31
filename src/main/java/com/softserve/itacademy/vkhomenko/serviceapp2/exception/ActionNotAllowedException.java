package com.softserve.itacademy.vkhomenko.serviceapp2.exception;


public class ActionNotAllowedException extends RuntimeException {
    public ActionNotAllowedException(String message) {
        super(message);
    }
}
