package com.example.demo.services;

import com.example.demo.domain.entities.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BookService {

    BookEntity addBook(BookEntity book);
    Page<BookEntity> readBooks(Pageable pageable);
    Page<BookEntity> listBooksByCategoryId(Pageable pageable,Long categoryId);
    Optional<BookEntity> readOneBook(Long id);


}
