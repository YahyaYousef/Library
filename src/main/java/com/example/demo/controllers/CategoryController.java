package com.example.demo.controllers;

import com.example.demo.domain.dto.CategoryDto;
import com.example.demo.domain.request.CategoryRequestBody;
import com.example.demo.domain.response.PaginationResponse;
import com.example.demo.usecaseimpl.category.CreateCategoryUseCaseImpl;
import com.example.demo.usecaseimpl.category.ListCategoriesByParentIdImpl;
import com.example.demo.usecaseimpl.category.ListCategoriesUseCaseImpl;
import lombok.extern.java.Log;
import org.springframework.data.domain.PageRequest;
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
    public PaginationResponse<CategoryDto> getAllCategories(@RequestParam(required = false,defaultValue = "${pageSettings.defaultPageNumber}") int page,
                                                            @RequestParam(required = false,defaultValue = "${pageSettings.defaultPageSize}") int size) {
        return listCategoriesUseCase.execute(PageRequest.of(page, size));
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryRequestBody category) {
        return ResponseEntity.ok(createCategoryUseCase.execute(category));
    }

    @GetMapping("/categories/parent/{parentId}")
    public List<CategoryDto> findCategoriesByParentId(@PathVariable Long parentId) {
       return listCategoriesByParentId.execute(parentId);
    }

}
