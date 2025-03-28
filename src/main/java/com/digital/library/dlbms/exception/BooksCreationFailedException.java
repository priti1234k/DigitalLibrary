package com.digital.library.dlbms.exception;

import lombok.Data;

@Data
public class BooksCreationFailedException extends RuntimeException{
    private String reason;

    public BooksCreationFailedException(String message) {
        super(message);
        this.reason = message;
    }
}
