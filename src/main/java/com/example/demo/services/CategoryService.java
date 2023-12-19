package com.example.demo.services;

import com.example.demo.domain.entities.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    CategoryEntity addCategory(CategoryEntity category);
    Page<CategoryEntity> readCategories(Pageable pageable);
    List<CategoryEntity> findCategoriesByParentId(Long id);
    Optional<CategoryEntity> findCategoryById(Long id);



}
