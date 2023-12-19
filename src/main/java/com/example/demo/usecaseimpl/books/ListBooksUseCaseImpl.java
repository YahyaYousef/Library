package com.example.demo.usecaseimpl.books;

import com.example.demo.config.PageMapper;
import com.example.demo.domain.dto.BookDto;
import com.example.demo.domain.entities.BookEntity;
import com.example.demo.domain.request.BooksListRequest;
import com.example.demo.domain.response.PaginationResponse;
import com.example.demo.mapper.Mapper;
import com.example.demo.services.BookService;
import com.example.demo.usecase.books.ListBooksUseCase;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


@Transactional
@Component
public class ListBooksUseCaseImpl implements ListBooksUseCase {

    private final BookService bookService;
    private final Mapper<BookEntity,BookDto> bookMapper;

    private final PageMapper<BookDto> bookDtoPageMapper;

    public ListBooksUseCaseImpl(BookService bookService, Mapper<BookEntity, BookDto> bookMapper, PageMapper<BookDto> bookDtoPageMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
        this.bookDtoPageMapper = bookDtoPageMapper;
    }



    @Override
    public PaginationResponse<BookDto> execute(BooksListRequest booksListRequest) {
        Page<BookEntity> bookEntities = booksListRequest.getCategoryID() == null?
                bookService.readBooks(booksListRequest.getPageable()):
                bookService.listBooksByCategoryId(booksListRequest.getPageable(),booksListRequest.getCategoryID());
        Page<BookDto> page = bookEntities.map(bookMapper::mapTO);
        return bookDtoPageMapper.convert(page);
    }
}
