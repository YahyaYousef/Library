package com.example.demo.services.impl;

import com.example.demo.customexception.NotFoundException;
import com.example.demo.domain.entities.UserEntity;
import com.example.demo.domain.entities.UsersImage;
import com.example.demo.repo.UserImageRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final UserImageRepo userImageRepo;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    public UserServiceImpl(UserRepo userRepo, UserImageRepo userImageRepo) {
        this.userRepo = userRepo;
        this.userImageRepo = userImageRepo;
    }


    @Override
    public UserEntity createUser(UserEntity user) {
        return userRepo.save(user);
    }

    @Override
    public Page<UserEntity> findAllUsers(Pageable pageable) {
        return userRepo.findAll(pageable);
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
    public UserEntity updateUserProfileImage(UUID userId,UsersImage usersImage) {
        Optional<UserEntity> byId = userRepo.findById(userId);
        if(byId.isEmpty()){
            throw new NotFoundException("User not found");
        }
        UserEntity user = byId.get();
        if(user.getImage() != null){
            Optional<UsersImage> image = userImageRepo.findById(usersImage.getImageId());
            if(image.isEmpty()){
                throw new NotFoundException("Image not found");
            }
            File imageFile = new File(image.get().getImagePath());
            boolean delete = imageFile.delete();
            if(!delete){
                logger.error("Image not deleted");
            }
            userImageRepo.deleteById(user.getImage());
            usersImage.setImageId(null);
        }
        UsersImage savedImage = userImageRepo.save(usersImage);
        byId.ifPresent(userEntity -> userEntity.setImage(savedImage.getImageId()));
        return userRepo.save(user);
    }

    @Override
    public Optional<UsersImage> getProfileImage(UUID imageId) {
        return userImageRepo.findById(imageId);
    }


}
