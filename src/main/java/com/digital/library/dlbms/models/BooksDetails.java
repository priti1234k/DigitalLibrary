package com.digital.library.dlbms.models;

import lombok.Data;

@Data
public class BooksDetails {
    private String title;
    private String author;
    private String genre;
    private AvailabilityStatus availabilityStatus;
}
