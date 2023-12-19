package com.example.demo.services.impl;

import com.example.demo.domain.entities.CategoryEntity;
import com.example.demo.repo.CategoryRepo;
import com.example.demo.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public CategoryEntity addCategory(CategoryEntity book) {
        return categoryRepo.save(book);
    }

    @Override
    public List<CategoryEntity> readCategories() {
        Iterable<CategoryEntity> categories = categoryRepo.findAll();
        return StreamSupport.stream(categories.spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public List<CategoryEntity> findCategoriesByParentId(Long id) {
        Iterable<CategoryEntity> allCategoryByParentId = categoryRepo.findAllCategoryByParentId(id);
        return StreamSupport.stream(allCategoryByParentId.spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryEntity> findCategoryById(Long id) {
        return categoryRepo.findById(id);
    }
}
