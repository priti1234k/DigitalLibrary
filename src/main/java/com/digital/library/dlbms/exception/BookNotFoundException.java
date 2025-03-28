package com.digital.library.dlbms.exception;

import lombok.Data;

@Data
public class BookNotFoundException extends RuntimeException{
    private String reason;

    public BookNotFoundException(String message) {
        super(message);
        this.reason = message;
    }
}
