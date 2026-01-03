package com.example.bank.exceptions;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError {

    private int status;
    private String message;
    private String path;
    private LocalDateTime timestamp;

    public ApiError(int status, String message, String path ) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
    // getters & setters
}
