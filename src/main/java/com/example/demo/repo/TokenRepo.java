package com.example.demo.repo;

import com.example.demo.domain.entities.BookEntity;
import com.example.demo.domain.entities.TokensEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepo extends CrudRepository<TokensEntity,String> {
}
