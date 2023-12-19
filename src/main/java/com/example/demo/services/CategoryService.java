package com.example.demo.services;

import com.example.demo.domain.entities.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    CategoryEntity addCategory(CategoryEntity category);
    List<CategoryEntity> readCategories();
    List<CategoryEntity> findCategoriesByParentId(Long id);
    Optional<CategoryEntity> findCategoryById(Long id);



}
