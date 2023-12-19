package com.example.demo.services;


import com.example.demo.domain.entities.UserEntity;
import com.example.demo.domain.entities.UsersImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

    UserEntity createUser(UserEntity user);
    Page<UserEntity> findAllUsers(Pageable pageable);
    Optional<UserEntity> findUserById(UUID id);
    Optional<UserEntity> findUserByUsername(String username);
    Optional<UserEntity> findUserByEmail(String email);
    UserEntity updateUserProfileImage(UUID userId,UsersImage usersImage);

    Optional<UsersImage> getProfileImage(UUID imageId);

}
