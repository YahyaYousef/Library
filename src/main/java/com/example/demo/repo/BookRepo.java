package com.example.demo.repo;

import com.example.demo.domain.entities.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends CrudRepository<BookEntity,Long>, PagingAndSortingRepository<BookEntity,Long> {

    Page<BookEntity> findAllBooksByCategoryId(Pageable pageable, Long categoryId);
}
