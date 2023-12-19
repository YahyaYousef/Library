package com.example.demo.services;


import com.example.demo.domain.entities.UserEntity;
import com.example.demo.domain.entities.UsersImage;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    UserEntity createUser(UserEntity user);
    List<UserEntity> findAllUsers();
    Optional<UserEntity> findUserById(UUID id);
    Optional<UserEntity> findUserByUsername(String username);
    Optional<UserEntity> findUserByEmail(String email);
    UserEntity updateUserProfileImage(UUID userId, UUID imageId,UsersImage usersImage);

    Optional<UsersImage> getProfileImage(UUID imageId);

}
