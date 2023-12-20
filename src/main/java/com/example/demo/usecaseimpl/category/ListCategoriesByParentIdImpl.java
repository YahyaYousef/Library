package com.example.demo.usecaseimpl.category;

import com.example.demo.customexception.InvalidRequestException;
import com.example.demo.domain.dto.CategoryDto;
import com.example.demo.domain.entities.CategoryEntity;
import com.example.demo.mapper.impl.CategoryMapperImpl;
import com.example.demo.services.CategoryService;
import com.example.demo.usecase.UseCase;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Component
public class ListCategoriesByParentIdImpl implements UseCase<Long, List<CategoryDto>> {

    private final CategoryService categoryService;
    private final CategoryMapperImpl categoryMapper;

    public ListCategoriesByParentIdImpl(CategoryService categoryService, CategoryMapperImpl categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDto> execute(Long parentId) {
        if (parentId == null) {
            throw new InvalidRequestException("parent_id can not be empty");
        }
        List<CategoryEntity> categoriesByParentId = categoryService.findCategoriesByParentId(parentId);
        return categoriesByParentId.stream().map(categoryMapper::mapTO).collect(Collectors.toList());
    }
}
