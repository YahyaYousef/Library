package com.example.demo.usecaseimpl.user;

import com.example.demo.customexception.InvalidRequestException;
import com.example.demo.customexception.user.UserAlreadyExistException;
import com.example.demo.domain.dto.UserDto;
import com.example.demo.domain.entities.UserEntity;
import com.example.demo.domain.request.UserRequestBody;
import com.example.demo.mapper.Mapper;
import com.example.demo.services.UserService;
import com.example.demo.usecase.user.CreateUserUseCase;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Pattern;

@Transactional
@Component
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CreateUserUseCaseImpl.class);
    private final UserService userService;
    private final Mapper<UserEntity,UserDto> userMapper;

    private final PasswordEncoder passwordEncoder;


    public CreateUserUseCaseImpl(UserService userService, Mapper<UserEntity, UserDto> userMapper, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto execute(UserRequestBody user) {
        if(user.getEmail() == null || user.getPhone() == null || user.getFullName() == null){
            logger.error("All Fields are required");
            throw new InvalidRequestException("All Fields are required");
        }
        Optional<UserEntity> userByUsername = userService.findUserByUsername(user.getUsername());
        if(userByUsername.isPresent()){
            logger.error("Username is already exist");
            throw new UserAlreadyExistException("Username is already exist");
        }
        Optional<UserEntity> userByEmail = userService.findUserByEmail(user.getEmail());
        if(userByEmail.isPresent()){
            logger.error("Email is already exist");
            throw new UserAlreadyExistException("Email is already exist");
        }
        String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$";
        boolean isValidEmail = patternMatches(user.getEmail(), emailRegex);
        if(!isValidEmail){
            throw new InvalidRequestException("Email is invalid");
        }
        String numberRegex= "^\\d{10}$";
        boolean isValidNumber = patternMatches(user.getPhone(), numberRegex);
        if(!isValidNumber){
            throw new InvalidRequestException("Phone number is invalid");
        }
        UserEntity userToSave = convertRequestToEntity(user);
        userToSave.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity userEntity = userService.createUser(userToSave);
        return userMapper.mapTO(userEntity);
    }

    private UserEntity convertRequestToEntity(UserRequestBody user) {
        return UserEntity.builder()
                .phone(user.getPhone())
                .email(user.getEmail())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .build();
    }

    public boolean patternMatches(String field, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(field)
                .matches();
    }
}
