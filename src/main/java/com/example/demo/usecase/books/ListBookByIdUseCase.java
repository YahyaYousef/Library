package com.example.demo.usecase.books;

import com.example.demo.domain.dto.BookDto;
import com.example.demo.usecase.UseCase;

public interface ListBookByIdUseCase extends UseCase<Long, BookDto> {
}
