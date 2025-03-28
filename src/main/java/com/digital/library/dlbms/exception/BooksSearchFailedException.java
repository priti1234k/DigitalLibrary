package com.digital.library.dlbms.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BooksSearchFailedException extends RuntimeException{
    private String reason;

    private HttpStatus status;

    public BooksSearchFailedException(String message, HttpStatus status) {
        super(message);
        this.reason = message;
        this.status = status;
    }
}
