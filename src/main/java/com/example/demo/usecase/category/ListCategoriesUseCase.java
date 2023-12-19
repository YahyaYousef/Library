package com.example.demo.usecase.category;

import com.example.demo.domain.dto.CategoryDto;
import com.example.demo.domain.response.PaginationResponse;
import com.example.demo.usecase.UseCase;
import org.springframework.data.domain.Pageable;


public interface ListCategoriesUseCase extends UseCase<Pageable, PaginationResponse<CategoryDto>> {
}
