package com.digital.library.dlbms.models;

import lombok.Data;

import java.util.List;

@Data
public class BookDetailsPage {

    private List<BooksDetails> booksDetailsList;

    private int pageNo;

    private int size;

    private int totalNoOfPage;
}
