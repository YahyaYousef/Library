package com.example.demo.services.impl;

import com.example.demo.domain.entities.BookEntity;
import com.example.demo.repo.BookRepo;
import com.example.demo.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public Page<BookEntity> readBooks(Pageable pageable) {
        return bookRepo.findAll(pageable);
    }

    @Override
    public Page<BookEntity> listBooksByCategoryId(Pageable pageable,Long categoryId) {
        return bookRepo.findAllBooksByCategoryId(pageable,categoryId);
    }

    @Override
    public Optional<BookEntity> readOneBook(Long id) {
        return bookRepo.findById(id);
    }
}
