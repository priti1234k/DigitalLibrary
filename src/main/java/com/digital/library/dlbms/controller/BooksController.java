package com.digital.library.dlbms.controller;


import com.digital.library.dlbms.exception.BooksSearchFailedException;
import com.digital.library.dlbms.models.BookDetailsPage;
import com.digital.library.dlbms.models.BooksDetails;
import com.digital.library.dlbms.repositories.entities.Book;
import com.digital.library.dlbms.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static com.digital.library.dlbms.constants.URIPaths.*;

@RestController
@RequestMapping(API_V1 + BOOKS)
public class BooksController {
    @Autowired
    private BooksService booksService;

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BooksDetails booksDetails){
        Book book = booksService.createBook(booksDetails);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<BookDetailsPage> viewListOfBooks(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize){
        BookDetailsPage listOfBooks = booksService.listOfBooks(pageNo, pageSize);
        return ResponseEntity.ok(listOfBooks);
    }

    @GetMapping(SEARCH)
    public ResponseEntity<List<BooksDetails>> searchBasedOnBookIdAndTitle(
            @RequestParam(value = "bookId", required = false) Long bookId,
            @RequestParam(value = "title", required = false) String title){
        if(Objects.nonNull(bookId) && Objects.nonNull(title)){
            throw new BooksSearchFailedException("Bad request, please submit anyone among book-id & title", HttpStatus.BAD_REQUEST);
        }else{
            if(Objects.nonNull(bookId)){
                List<BooksDetails> listOfBooks = booksService.listOfBooksBasedOnId(bookId);
                return ResponseEntity.ok(listOfBooks);
            }else if(Objects.nonNull(title)){
                List<BooksDetails> listOfBooks = booksService.listOfBooksBasedOnTitle(title);
                return ResponseEntity.ok(listOfBooks);
            }else{
                throw new BooksSearchFailedException("Bad request, please submit anyone among book-id & title", HttpStatus.BAD_REQUEST);
            }
        }
    }


    @PutMapping
    public ResponseEntity<?> updateBookDetails(@RequestParam("bookId") Long bookId, @RequestBody BooksDetails booksDetails){
        booksService.updateBookDetails(bookId, booksDetails);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping
    public ResponseEntity<?> deleteBook(@RequestParam("bookId") Long bookid){
        booksService.deleteBook(bookid);
        return ResponseEntity.ok().build();
    }

}
