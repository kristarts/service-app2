package com.softserve.itacademy.vkhomenko.serviceapp2.exception;

public class InvalidUuidException extends IllegalArgumentException {
    public InvalidUuidException(String s) {
        super(s);
    }
}
