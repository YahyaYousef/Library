package com.example.demo.usecaseimpl.books;

import com.example.demo.customexception.InvalidRequestException;
import com.example.demo.customexception.NotFoundException;
import com.example.demo.domain.dto.BookDto;
import com.example.demo.domain.entities.BookEntity;
import com.example.demo.mapper.impl.BookMapperImpl;
import com.example.demo.services.BookService;
import com.example.demo.usecase.books.ListBookByIdUseCase;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Transactional
@Component
public class ListBookByIdUseCaseImpl implements ListBookByIdUseCase {

    private final BookService bookService;
    private final BookMapperImpl bookMapper;

    public ListBookByIdUseCaseImpl(BookService bookService, BookMapperImpl bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDto execute(Long id) {
        if (id == null) {
            throw new InvalidRequestException("id can not be null");
        }
        Optional<BookEntity> bookEntity = bookService.readOneBook(id);
        if (bookEntity.isPresent()) {
            return bookMapper.mapTO(bookEntity.get());
        }
        throw new NotFoundException("Book not found");
    }
}
