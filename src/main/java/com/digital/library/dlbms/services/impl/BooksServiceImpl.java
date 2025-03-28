package com.digital.library.dlbms.services.impl;

import ch.qos.logback.core.util.StringUtil;
import com.digital.library.dlbms.exception.BookDeleteFailedException;
import com.digital.library.dlbms.exception.BookNotFoundException;
import com.digital.library.dlbms.exception.BooksCreationFailedException;
import com.digital.library.dlbms.exception.BooksUpdateFailedException;
import com.digital.library.dlbms.models.AvailabilityStatus;
import com.digital.library.dlbms.models.BookDetailsPage;
import com.digital.library.dlbms.models.BooksDetails;
import com.digital.library.dlbms.repositories.BooksRepository;
import com.digital.library.dlbms.repositories.entities.Book;
import com.digital.library.dlbms.services.BooksService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    private BooksRepository booksRepository;

    @Override
    public Book createBook(BooksDetails booksDetails) {

        if(Objects.nonNull(booksDetails) &&
                !StringUtil.isNullOrEmpty(booksDetails.getTitle()) &&
                !StringUtil.isNullOrEmpty(booksDetails.getAuthor()) &&
                !StringUtil.isNullOrEmpty(booksDetails.getGenre())){

            // Set default availability status if null
            if (booksDetails.getAvailabilityStatus() == null) {
                booksDetails.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
            }

            Book book = convertToBook(booksDetails);
            try {
                book = booksRepository.save(book);
            } catch (Exception ex) {
                throw new BooksCreationFailedException("Failed to create book: " + ex.getMessage());
            }

            // return BookId
            return book;

        }else{
            throw new BooksCreationFailedException("Invalid Book Details");
        }

    }

    private Book convertToBook(BooksDetails booksDetails) {
        Book book = new Book();
        book.setTitle(booksDetails.getTitle());
        book.setAuthor(booksDetails.getAuthor());
        book.setGenre(booksDetails.getGenre());
        book.setAvailabilityStatus(booksDetails.getAvailabilityStatus());
        return book;
    }

    @Override
    public BookDetailsPage listOfBooks(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Book> page = booksRepository.findAllBooks(pageable);
        List<Book> list = page.stream().toList();
        List<BooksDetails> booksDetailsList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)) {
            for (Book book : list) {
                booksDetailsList.add(convertToBookDetails(book));
            }
        }else{
            throw new BookNotFoundException("No Book present in DB");
        }
        BookDetailsPage bookDetailsPage = new BookDetailsPage();
        bookDetailsPage.setBooksDetailsList(booksDetailsList);
        bookDetailsPage.setPageNo(pageNo);
        bookDetailsPage.setSize(pageSize);
        bookDetailsPage.setTotalNoOfPage(page.getTotalPages());
        return bookDetailsPage;
    }

    @Override
    public List<BooksDetails> listOfBooksBasedOnId(Long bookId) {
        List<Book> list = booksRepository.findByBookId(bookId);
        List<BooksDetails> booksDetailsList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)) {
            for (Book book : list) {
                booksDetailsList.add(convertToBookDetails(book));
            }
        }else{
            throw new BookNotFoundException("No Book present in DB");
        }
        return booksDetailsList;
    }

    @Override
    public List<BooksDetails> listOfBooksBasedOnTitle(String title) {
        List<Book> list = booksRepository.findByBookTitle(title);
        List<BooksDetails> booksDetailsList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)) {
            for (Book book : list) {
                booksDetailsList.add(convertToBookDetails(book));
            }
        }else{
            throw new BookNotFoundException("No Book present in DB");
        }
        return booksDetailsList;
    }

    @Override
    public Book updateBookDetails(Long bookId, BooksDetails booksDetails) {
        if(Objects.nonNull(bookId)
                && Objects.nonNull(booksDetails)
                && (!StringUtil.isNullOrEmpty(booksDetails.getTitle())
                    || !StringUtil.isNullOrEmpty(booksDetails.getAuthor())
                    || !StringUtil.isNullOrEmpty(booksDetails.getGenre())
                    || Objects.nonNull(booksDetails.getAvailabilityStatus()))){
            try {
                // Retrieve the Book using Book_Id
                Optional<Book> bookOptional = booksRepository.findById(bookId);
                if (bookOptional.isPresent()) {
                    Book book = bookOptional.get();
                    if(!StringUtil.isNullOrEmpty(booksDetails.getTitle())){
                        book.setTitle(booksDetails.getTitle());
                    }
                    if(!StringUtil.isNullOrEmpty(booksDetails.getAuthor())){
                        book.setAuthor(booksDetails.getAuthor());
                    }
                    if(!StringUtil.isNullOrEmpty(booksDetails.getGenre())){
                        book.setGenre(booksDetails.getGenre());
                    }
                    if(Objects.nonNull(booksDetails.getAvailabilityStatus())){
                        book.setAvailabilityStatus(booksDetails.getAvailabilityStatus());
                    }

                    // Save the updated Books object in the DB
                    return booksRepository.save(book);
                } else {
                    // Throw exception
                    throw new BookNotFoundException("Book not present in db");
                }
            } catch (Exception ex) {
                // Throw exception
                throw new BooksUpdateFailedException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            throw new BooksUpdateFailedException("Bad request", HttpStatus.BAD_REQUEST);
        }
    }

    private BooksDetails convertToBookDetails(Book book) {
        BooksDetails booksDetails = new BooksDetails();
        booksDetails.setTitle(book.getTitle());
        booksDetails.setAuthor(book.getAuthor());
        booksDetails.setGenre(book.getGenre());
        booksDetails.setAvailabilityStatus(book.getAvailabilityStatus());
        return booksDetails;
    }


    @Transactional
    @Override
    public void deleteBook(Long bookId) {
        if(Objects.nonNull(bookId)){
            try {
                booksRepository.deleteById(bookId);
            } catch (Exception ex) {
                // Throw exception
                throw new BookDeleteFailedException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            throw new BookDeleteFailedException("Bad request", HttpStatus.BAD_REQUEST);
        }
    }
}
