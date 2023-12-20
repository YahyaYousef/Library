package com.example.demo.usecaseimpl.category;

import com.example.demo.customexception.InvalidRequestException;
import com.example.demo.customexception.NotFoundException;
import com.example.demo.domain.dto.CategoryDto;
import com.example.demo.domain.entities.CategoryEntity;
import com.example.demo.domain.request.CategoryRequestBody;
import com.example.demo.mapper.impl.CategoryMapperImpl;
import com.example.demo.services.CategoryService;
import com.example.demo.usecase.UseCase;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Transactional
@Component
public class CreateCategoryUseCaseImpl implements UseCase<CategoryRequestBody, CategoryDto> {

    private final CategoryService categoryService;
    private final CategoryMapperImpl categoryMapper;

    public CreateCategoryUseCaseImpl(CategoryService categoryService, CategoryMapperImpl categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryDto execute(CategoryRequestBody category) {
        if (category.getTitle() == null) {
            throw new InvalidRequestException("Title can not be empty");
        }
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .title(category.getTitle())
                .build();
        //Fill parent category
        if (category.getParentId() != null && category.getParentId() > 0) {
            Optional<CategoryEntity> parentCategory = categoryService.findCategoryById(category.getParentId());
            if (parentCategory.isEmpty()) {
                throw new NotFoundException("Parent Not Found");
            }
            categoryEntity.setParent(parentCategory.get());
        }
        return categoryMapper.mapTO(categoryService.addCategory(categoryEntity));
    }
}
