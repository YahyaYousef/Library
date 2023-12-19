package com.example.demo.controllers;

import com.example.demo.domain.dto.BookDto;

import com.example.demo.domain.request.BookRequestBody;

import com.example.demo.domain.request.BooksListRequest;
import com.example.demo.domain.response.PaginationResponse;
import com.example.demo.usecase.books.ListBookByIdUseCase;
import com.example.demo.usecase.books.ListBooksUseCase;
import com.example.demo.usecaseimpl.books.CreateBookUseCaseImpl;

import lombok.extern.java.Log;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public PaginationResponse<BookDto> getBooks(@RequestParam(required = false,defaultValue = "${pageSettings.defaultPageNumber}") int page,
                                                @RequestParam(required = false,defaultValue = "${pageSettings.defaultPageSize}") int size,
                                                @RequestParam(required = false) Long categoryId) {
        BooksListRequest booksListRequest = BooksListRequest.builder()
                .categoryID(categoryId)
                .pageable(PageRequest.of(page,size))
                .build();
        return listBooksUseCase.execute(booksListRequest);
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
