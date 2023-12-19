package com.example.demo.usecase.books;

import com.example.demo.domain.dto.BookDto;
import com.example.demo.domain.request.BooksListRequest;
import com.example.demo.domain.response.PaginationResponse;
import com.example.demo.usecase.UseCase;

public interface ListBooksUseCase extends UseCase<BooksListRequest, PaginationResponse<BookDto>> {
}
