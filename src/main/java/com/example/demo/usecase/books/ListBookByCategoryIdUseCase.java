package com.example.demo.usecase.books;

import com.example.demo.domain.dto.BookDto;
import com.example.demo.usecase.UseCase;

import java.util.List;

public interface ListBookByCategoryIdUseCase extends UseCase<Long, List<BookDto>> {
}
