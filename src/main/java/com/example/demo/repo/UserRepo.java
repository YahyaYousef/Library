package com.example.demo.repo;

import com.example.demo.domain.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepo extends CrudRepository<UserEntity, UUID> {

    Optional<UserEntity> findUserByUsername(String username);
    Optional<UserEntity> findUserByEmail(String email);
}
