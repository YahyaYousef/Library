package com.example.demo.usecaseimpl.category;

import com.example.demo.domain.dto.CategoryDto;
import com.example.demo.domain.entities.CategoryEntity;
import com.example.demo.mapper.Mapper;
import com.example.demo.services.CategoryService;
import com.example.demo.usecase.UseCase;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Component
public class ListCategoriesUseCaseImpl implements UseCase<Object, List<CategoryDto>> {
    private final CategoryService categoryService;
    private final Mapper<CategoryEntity, CategoryDto> categoryMapper;

    public ListCategoriesUseCaseImpl(CategoryService categoryService, Mapper<CategoryEntity, CategoryDto> categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDto> execute(Object o) {
        List<CategoryEntity> categoryEntityList = categoryService.readCategories();
        return categoryEntityList.stream().map(categoryMapper::mapTO).collect(Collectors.toList());
    }
}
