package com.digital.library.dlbms.repositories;

import com.digital.library.dlbms.repositories.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT * FROM BOOKS", nativeQuery = true)
    Page<Book> findAllBooks(Pageable pageable);

    @Query(value = "SELECT * FROM BOOKS WHERE BOOK_ID = :bookId", nativeQuery = true)
    List<Book> findByBookId(@Param("bookId") Long bookid);

    @Query(value = "SELECT * FROM BOOKS WHERE TITLE LIKE %:title%", nativeQuery = true)
    List<Book> findByBookTitle(@Param("title") String title);
}
