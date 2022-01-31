package com.softserve.itacademy.vkhomenko.serviceapp2.exception.handle;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
public abstract class BasicError implements Error {

    private int statusCode;
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss Z")
    private ZonedDateTime timestamp;
    private String message;

    public BasicError() {
        this.timestamp = ZonedDateTime.now();
    }

    public BasicError(HttpStatus status) {
        this();
        this.status = status;
        this.statusCode = status.value();
        this.message = "Unknown error";
    }

    public BasicError(HttpStatus status, String message) {
        this(status);
        this.message = message;
    }

    public BasicError(HttpStatus status, Throwable ex) {
        this(status, ex.getMessage());
    }
}
