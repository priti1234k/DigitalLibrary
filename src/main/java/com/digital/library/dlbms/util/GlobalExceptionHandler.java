package com.digital.library.dlbms.util;

import com.digital.library.dlbms.exception.*;
import com.digital.library.dlbms.models.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> bookNotFound(HttpServletRequest req, BookNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getReason());
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }

    @ExceptionHandler(value = BooksUpdateFailedException.class)
    public ResponseEntity<ErrorResponse> bookUpdateFailed(HttpServletRequest req, BooksUpdateFailedException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getReason());
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }

    @ExceptionHandler(value = BooksCreationFailedException.class)
    public ResponseEntity<ErrorResponse> bookCreationFailed(HttpServletRequest req, BooksCreationFailedException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getReason());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
