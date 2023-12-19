package com.example.demo.usecase.category;

import com.example.demo.domain.dto.CategoryDto;
import com.example.demo.usecase.UseCase;

import java.util.List;

public interface ListCategoriesUseCase extends UseCase<Object, List<CategoryDto>> {
}
