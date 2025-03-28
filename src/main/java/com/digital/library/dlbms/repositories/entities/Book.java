package com.digital.library.dlbms.repositories.entities;

import com.digital.library.dlbms.models.AvailabilityStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "books",
        indexes = @Index(name = "idx_book_title", columnList = "title"))
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "genre")
    private String genre;

    @Enumerated(EnumType.STRING)
    @Column(name = "availability_status", nullable = false)
    private AvailabilityStatus availabilityStatus;

}

