package com.digital.library.dlbms.services;

import com.digital.library.dlbms.models.BookDetailsPage;
import com.digital.library.dlbms.models.BooksDetails;
import com.digital.library.dlbms.repositories.entities.Book;
import jakarta.transaction.Transactional;

import java.util.List;

public interface BooksService {
    Book createBook(BooksDetails booksDetails);

    BookDetailsPage listOfBooks(int pageNo, int pageSize);

    List<BooksDetails> listOfBooksBasedOnId(Long bookId);

    List<BooksDetails> listOfBooksBasedOnTitle(String title);

    Book updateBookDetails(Long bookId, BooksDetails booksDetails);

    @Transactional
    void deleteBook(Long bookid);

}
