package com.example.demo.usecase.category;

import com.example.demo.domain.dto.CategoryDto;
import com.example.demo.domain.request.CategoryRequestBody;
import com.example.demo.usecase.UseCase;

public interface CreateCategoryUseCase extends UseCase<CategoryRequestBody, CategoryDto> {
}
