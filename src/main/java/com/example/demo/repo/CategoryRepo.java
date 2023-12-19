package com.example.demo.repo;

import com.example.demo.domain.entities.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends CrudRepository<CategoryEntity,Long>, PagingAndSortingRepository<CategoryEntity,Long> {

    Iterable<CategoryEntity> findAllCategoryByParentId(Long id);
}
