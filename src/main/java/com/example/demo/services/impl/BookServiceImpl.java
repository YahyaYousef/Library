package com.example.demo.services.impl;

import com.example.demo.domain.entities.BookEntity;
import com.example.demo.repo.BookRepo;
import com.example.demo.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;

    public BookServiceImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public BookEntity addBook(BookEntity book) {
        return bookRepo.save(book);
    }

    @Override
    public List<BookEntity> readBooks() {
        Iterable<BookEntity> all = bookRepo.findAll();
        return StreamSupport.stream(all.spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public Optional<BookEntity> readOneBook(Long id) {
        return bookRepo.findById(id);
    }
}
