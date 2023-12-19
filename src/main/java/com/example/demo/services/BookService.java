package com.example.demo.services;

import com.example.demo.domain.dto.BookDto;
import com.example.demo.domain.entities.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {

    BookEntity addBook(BookEntity book);
    List<BookEntity> readBooks();
    Optional<BookEntity> readOneBook(Long id);


}
