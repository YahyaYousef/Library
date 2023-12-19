package com.example.demo.services.impl;

import com.example.demo.domain.entities.UserEntity;
import com.example.demo.domain.entities.UsersImage;
import com.example.demo.repo.UserImageRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final UserImageRepo userImageRepo;

    public UserServiceImpl(UserRepo userRepo, UserImageRepo userImageRepo) {
        this.userRepo = userRepo;
        this.userImageRepo = userImageRepo;
    }


    @Override
    public UserEntity createUser(UserEntity user) {
        return userRepo.save(user);
    }

    @Override
    public List<UserEntity> findAllUsers() {
        Iterable<UserEntity> allUsers = userRepo.findAll();
        return StreamSupport.stream(allUsers.spliterator(),false).collect(Collectors.toList());
    }

    @Override
    public Optional<UserEntity> findUserById(UUID id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<UserEntity> findUserByUsername(String email) {
        return userRepo.findUserByUsername(email);
    }

    @Override
    public Optional<UserEntity> findUserByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }

    @Override
    public UserEntity updateUserProfileImage(UUID userId, UUID imageId,UsersImage usersImage) {
        Optional<UserEntity> byId = userRepo.findById(userId);
        byId.ifPresent(userEntity -> userEntity.setImage(imageId));
        userImageRepo.save(usersImage);
        return userRepo.save(byId.get());
    }

    @Override
    public Optional<UsersImage> getProfileImage(UUID imageId) {
        return userImageRepo.findImageByImageId(imageId);
    }


}