package com.digital.library.dlbms.models;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse {
    private String error;
    private HttpStatus status;
    private String stackTrace;
}
