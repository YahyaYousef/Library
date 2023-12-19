package com.example.demo.usecaseimpl.books;

import com.example.demo.customexception.InvalidRequestException;
import com.example.demo.customexception.NotFoundException;
import com.example.demo.domain.dto.BookDto;
import com.example.demo.domain.entities.BookEntity;
import com.example.demo.domain.entities.CategoryEntity;
import com.example.demo.domain.request.BookRequestBody;
import com.example.demo.mapper.Mapper;
import com.example.demo.services.BookService;
import com.example.demo.services.CategoryService;
import com.example.demo.usecase.books.CreateBookUseCase;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Transactional
@Component
public class CreateBookUseCaseImpl implements CreateBookUseCase {

    private final BookService bookService;
    private final CategoryService categoryService;
    private final Mapper<BookEntity, BookDto> bookMapper;

    public CreateBookUseCaseImpl(BookService bookService, CategoryService categoryService, Mapper<BookEntity, BookDto> bookMapper) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.bookMapper = bookMapper;
    }


    @Override
    public BookDto execute(BookRequestBody book) {
        if (book.getTitle() == null) {
            throw new InvalidRequestException("Title can't be null");
        }
        if (book.getUrl() == null) {
            throw new InvalidRequestException("Url can't be null");
        }
        if (book.getCategoryId() == null) {
            throw new InvalidRequestException("Can not add book without a category");
        }
        //Search for a category
        Optional<CategoryEntity> categoryById = categoryService.findCategoryById(book.getCategoryId());
        if (categoryById.isEmpty()) {
            throw new NotFoundException("Category not found");
        }
        BookDto bookDto = convertRequestToBookDTO(book);
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        bookEntity.setCategory(categoryById.get());
        return bookMapper.mapTO(bookService.addBook(bookEntity));
    }

    private BookDto convertRequestToBookDTO(BookRequestBody book) {
        return BookDto.builder()
                .url(book.getUrl())
                .title(book.getTitle())
                .build();
    }
}
