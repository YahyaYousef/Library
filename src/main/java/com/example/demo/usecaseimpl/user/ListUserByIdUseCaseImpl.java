package com.example.demo.usecaseimpl.user;

import com.example.demo.customexception.InvalidRequestException;
import com.example.demo.customexception.NotFoundException;
import com.example.demo.domain.dto.UserDto;
import com.example.demo.domain.entities.UserEntity;
import com.example.demo.mapper.Mapper;
import com.example.demo.services.UserService;
import com.example.demo.usecase.user.ListUserByIdUseCase;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Transactional
@Component
public class ListUserByIdUseCaseImpl implements ListUserByIdUseCase {

    public final UserService userService;
    public final Mapper<UserEntity,UserDto> userMapper;
    private static final Logger logger = LoggerFactory.getLogger(ListUserByIdUseCaseImpl.class);

    public ListUserByIdUseCaseImpl(UserService userService, Mapper<UserEntity, UserDto> userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto execute(UUID id) {
        if(id == null){
            logger.error("Id is Null");
            throw new InvalidRequestException("Id is empty");
        }
        Optional<UserEntity> userById = userService.findUserById(id);
        if(userById.isPresent()){
            return userMapper.mapTO(userById.get());
        }
        throw new NotFoundException("User Not Found");
    }
}
