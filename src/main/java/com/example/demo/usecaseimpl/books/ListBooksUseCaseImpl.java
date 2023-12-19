package com.example.demo.usecaseimpl.books;

import com.example.demo.domain.dto.BookDto;
import com.example.demo.domain.entities.BookEntity;
import com.example.demo.domain.request.BookRequestBody;
import com.example.demo.mapper.Mapper;
import com.example.demo.services.BookService;
import com.example.demo.usecase.books.CreateBookUseCase;
import com.example.demo.usecase.books.ListBooksUseCase;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Component
public class ListBooksUseCaseImpl implements ListBooksUseCase {

    private final BookService bookService;
    private final Mapper<BookEntity,BookDto> bookMapper;

    public ListBooksUseCaseImpl(BookService bookService, Mapper<BookEntity, BookDto> bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookDto> execute(Object o) {
        List<BookEntity> bookEntities = bookService.readBooks();
        return bookEntities.stream().map(bookMapper::mapTO).collect(Collectors.toList());
    }
}
