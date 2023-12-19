package com.example.demo.usecaseimpl.user;

import com.example.demo.domain.dto.UserDto;
import com.example.demo.domain.entities.UserEntity;
import com.example.demo.mapper.Mapper;
import com.example.demo.services.UserService;
import com.example.demo.usecase.UseCase;
import com.example.demo.usecase.user.ListUsersUseCase;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Component
public class ListUsersUseCaseImpl implements ListUsersUseCase {

    public final UserService userService;
    private final Mapper<UserEntity,UserDto> userMapper;

    public ListUsersUseCaseImpl(UserService userService, Mapper<UserEntity, UserDto> userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    private static final Logger logger = LoggerFactory.getLogger(ListUsersUseCaseImpl.class);
    @Override
    public List<UserDto> execute(Object o) {
        logger.info("Start fetching data");
        List<UserEntity> allUsers = userService.findAllUsers();
        logger.info("Data length is: " + allUsers.size());
        return allUsers.stream().map(userMapper::mapTO).collect(Collectors.toList());
    }
}
