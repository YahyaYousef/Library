package com.example.demo.usecaseimpl.user;

import com.example.demo.customexception.GenericException;
import com.example.demo.customexception.InvalidRequestException;
import com.example.demo.customexception.NotFoundException;
import com.example.demo.domain.dto.UserDto;
import com.example.demo.domain.entities.UserEntity;
import com.example.demo.domain.entities.UsersImage;
import com.example.demo.domain.request.ImageUploadRequest;
import com.example.demo.mapper.Mapper;
import com.example.demo.services.UserService;
import com.example.demo.usecase.user.UploadProfileImageUseCase;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Transactional
@Component
public class UploadProfileImageUseCaseImpl implements UploadProfileImageUseCase {

    @Value("${file.upload-dir}")
    private String uploadDir;
    private final UserService userService;

    private final Mapper<UserEntity, UserDto> userMapper;
    private static final Logger logger = LoggerFactory.getLogger(UploadProfileImageUseCaseImpl.class);

    public UploadProfileImageUseCaseImpl(UserService userService, Mapper<UserEntity, UserDto> userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto execute(ImageUploadRequest request) {
        logger.debug("User ID: " + request.getUserId());
        if (request.getFile().isEmpty()) {
            throw new InvalidRequestException("Image is not uploaded");
        }
        Optional<UserEntity> userById = userService.findUserById(request.getUserId());
        if (userById.isEmpty()) {
            throw new NotFoundException("User is not found");
        }
        Path filePath = Paths.get(uploadDir, userById.get().getId() + "_" + System.currentTimeMillis() + "_" + request.getFile().getOriginalFilename());
        createFileIfNotExist(filePath.toFile());
        try {
            Files.copy(request.getFile().getInputStream(), filePath);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new GenericException("Error in image saving");
        }
        UsersImage usersImage = UsersImage.builder()
                .user(userById.get())
                .imageId(userById.get().getImage())
                .imagePath(filePath.toAbsolutePath().toString())
                .build();

        return userMapper.mapTO(userService.updateUserProfileImage(request.getUserId(), usersImage));
    }

    private void createFileIfNotExist(File file) {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }


}
