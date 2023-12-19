package com.example.demo.usecase.books;

import com.example.demo.domain.dto.BookDto;
import com.example.demo.usecase.UseCase;

import java.util.List;

public interface ListBooksUseCase extends UseCase<Object, List<BookDto>> {
}
