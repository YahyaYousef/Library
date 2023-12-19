package com.example.demo.usecaseimpl.books;

import com.example.demo.domain.dto.BookDto;
import com.example.demo.domain.entities.BookEntity;
import com.example.demo.mapper.Mapper;
import com.example.demo.services.BookService;
import com.example.demo.usecase.books.ListBookByCategoryIdUseCase;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Component
public class ListBookByCategoryIdUseCaseImpl implements ListBookByCategoryIdUseCase {

    private final BookService bookService;

    private final Mapper<BookEntity,BookDto> bookMapper;
    public ListBookByCategoryIdUseCaseImpl(BookService bookService, Mapper<BookEntity, BookDto> bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookDto> execute(Long categoryId) {
        List<BookEntity> bookEntities = bookService.listBooksByCategoryId(categoryId);
        return bookEntities.stream().map(bookMapper::mapTO).collect(Collectors.toList());
    }
}
