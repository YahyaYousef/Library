package com.example.demo.repo;

import com.example.demo.domain.entities.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends CrudRepository<BookEntity,Long> {
}
