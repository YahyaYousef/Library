package com.example.demo.usecaseimpl.user;

import com.example.demo.customexception.GenericException;
import com.example.demo.customexception.NotFoundException;
import com.example.demo.domain.entities.UsersImage;
import com.example.demo.services.UserService;
import com.example.demo.usecase.user.GetProfileImageUseCase;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Component
public class GetProfileImageUseCaseImpl implements GetProfileImageUseCase {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(GetProfileImageUseCaseImpl.class);

    public GetProfileImageUseCaseImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public byte[] execute(UUID imageId) {
        Optional<UsersImage> profileImage = userService.getProfileImage(imageId);
        if(profileImage.isPresent()){
            File file = new File(profileImage.get().getImagePath());
            Path path = file.toPath();
            try {
                logger.info("File Path: "+ path.toFile().getAbsolutePath());
                return Files.readAllBytes(path);
            } catch (IOException e) {
                throw new GenericException(e.getMessage());
            }
        }
        throw new NotFoundException("Image not found");
    }
}
