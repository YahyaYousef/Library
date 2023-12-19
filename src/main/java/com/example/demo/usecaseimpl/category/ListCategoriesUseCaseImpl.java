package com.example.demo.usecaseimpl.category;

import com.example.demo.config.PageMapper;
import com.example.demo.domain.dto.CategoryDto;
import com.example.demo.domain.entities.CategoryEntity;
import com.example.demo.domain.response.PaginationResponse;
import com.example.demo.mapper.Mapper;
import com.example.demo.services.CategoryService;
import com.example.demo.usecase.category.ListCategoriesUseCase;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Transactional
@Component
public class ListCategoriesUseCaseImpl implements ListCategoriesUseCase {
    private final CategoryService categoryService;
    private final Mapper<CategoryEntity, CategoryDto> categoryMapper;

    private final PageMapper<CategoryDto> pageMapper;

    public ListCategoriesUseCaseImpl(CategoryService categoryService, Mapper<CategoryEntity, CategoryDto> categoryMapper, PageMapper<CategoryDto> pageMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
        this.pageMapper = pageMapper;
    }

    @Override
    public PaginationResponse<CategoryDto> execute(Pageable pageable) {
        Page<CategoryEntity> categoryEntityList = categoryService.readCategories(pageable);
        return pageMapper.convert(categoryEntityList.map(categoryMapper::mapTO));
    }
}
