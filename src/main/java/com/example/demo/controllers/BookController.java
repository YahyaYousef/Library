package com.example.demo.controllers;

import com.example.demo.domain.dto.BookDto;
import com.example.demo.domain.entities.BookEntity;
import com.example.demo.domain.entities.CategoryEntity;
import com.example.demo.domain.request.BookRequestBody;
import com.example.demo.domain.response.ErrorModel;
import com.example.demo.mapper.Mapper;
import com.example.demo.services.BookService;
import com.example.demo.services.CategoryService;
import com.example.demo.usecase.books.ListBookByIdUseCase;
import com.example.demo.usecase.books.ListBooksUseCase;
import com.example.demo.usecaseimpl.books.CreateBookUseCaseImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/api")
@Log
public class BookController {


    public final CreateBookUseCaseImpl createBookUseCase;
    public final ListBooksUseCase listBooksUseCase;
    public final ListBookByIdUseCase listBookByIdUseCase;

    public BookController(CreateBookUseCaseImpl createBookUseCase, ListBooksUseCase listBooksUseCase, ListBookByIdUseCase listBookByIdUseCase) {
        this.createBookUseCase = createBookUseCase;
        this.listBooksUseCase = listBooksUseCase;
        this.listBookByIdUseCase = listBookByIdUseCase;

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


}
