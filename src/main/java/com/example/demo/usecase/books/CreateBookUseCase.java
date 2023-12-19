package com.example.demo.usecase.books;

import com.example.demo.domain.dto.BookDto;
import com.example.demo.domain.request.BookRequestBody;
import com.example.demo.usecase.UseCase;

public interface CreateBookUseCase extends UseCase<BookRequestBody, BookDto> {
}
