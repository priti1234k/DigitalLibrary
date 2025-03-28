package com.digital.library.dlbms.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BookDeleteFailedException extends RuntimeException{
    private String reason;

    private HttpStatus status;

    public BookDeleteFailedException(String message, HttpStatus status) {
        super(message);
        this.reason = message;
        this.status = status;
    }
}
