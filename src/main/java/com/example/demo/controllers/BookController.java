package com.example.demo.controllers;

import com.example.demo.domain.dto.BookDto;

import com.example.demo.domain.request.BookRequestBody;

import com.example.demo.usecase.books.ListBookByCategoryIdUseCase;
import com.example.demo.usecase.books.ListBookByIdUseCase;
import com.example.demo.usecase.books.ListBooksUseCase;
import com.example.demo.usecaseimpl.books.CreateBookUseCaseImpl;

import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("/api")
@Log
public class BookController {


    public final CreateBookUseCaseImpl createBookUseCase;
    public final ListBooksUseCase listBooksUseCase;
    public final ListBookByIdUseCase listBookByIdUseCase;
    public final ListBookByCategoryIdUseCase listBookByCategoryId;

    public BookController(CreateBookUseCaseImpl createBookUseCase, ListBooksUseCase listBooksUseCase, ListBookByIdUseCase listBookByIdUseCase, ListBookByCategoryIdUseCase listBookByCategoryId) {
        this.createBookUseCase = createBookUseCase;
        this.listBooksUseCase = listBooksUseCase;
        this.listBookByIdUseCase = listBookByIdUseCase;
        this.listBookByCategoryId = listBookByCategoryId;
    }


    @GetMapping("/books")
    public List<BookDto> getBooks() {
        return listBooksUseCase.execute(null);
    }

    @PostMapping("/books")
    public ResponseEntity<BookDto> saveBook(@RequestBody BookRequestBody book) {
        return ResponseEntity.ok(createBookUseCase.execute(book));
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(listBookByIdUseCase.execute(id));
    }

    @GetMapping("/booksByCategory")
    public List<BookDto> listBooksByCategoryId(@RequestParam Long categoryId) {
        return listBookByCategoryId.execute(categoryId);
    }


}
