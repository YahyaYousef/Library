package com.example.demo.controllers;

import com.example.demo.domain.dto.CategoryDto;
import com.example.demo.domain.request.CategoryRequestBody;
import com.example.demo.usecaseimpl.category.CreateCategoryUseCaseImpl;
import com.example.demo.usecaseimpl.category.ListCategoriesByParentIdImpl;
import com.example.demo.usecaseimpl.category.ListCategoriesUseCaseImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Log
public class CategoryController {

    private final CreateCategoryUseCaseImpl createCategoryUseCase;
    private final ListCategoriesByParentIdImpl listCategoriesByParentId;
    private final ListCategoriesUseCaseImpl listCategoriesUseCase;

    public CategoryController(CreateCategoryUseCaseImpl createCategoryUseCase, ListCategoriesByParentIdImpl listCategoriesByParentId, ListCategoriesUseCaseImpl listCategoriesUseCase) {
        this.createCategoryUseCase = createCategoryUseCase;
        this.listCategoriesByParentId = listCategoriesByParentId;
        this.listCategoriesUseCase = listCategoriesUseCase;
    }

    @GetMapping("/categories")
    public List<CategoryDto> getAllCategories() {
        return listCategoriesUseCase.execute(null);
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryRequestBody category) {
        return ResponseEntity.ok(createCategoryUseCase.execute(category));
    }

    @GetMapping("/categories/parent/{parentId}")
    public List<CategoryDto> findCategoriesByParentId(@PathVariable Long parentId) throws JsonProcessingException {
       return listCategoriesByParentId.execute(parentId);
    }

}
