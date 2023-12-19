package com.example.demo.usecaseimpl.user;

import com.example.demo.config.PageMapper;
import com.example.demo.domain.dto.UserDto;
import com.example.demo.domain.entities.UserEntity;
import com.example.demo.domain.response.PaginationResponse;
import com.example.demo.mapper.Mapper;
import com.example.demo.services.UserService;
import com.example.demo.usecase.user.ListUsersUseCase;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Transactional
@Component
public class ListUsersUseCaseImpl implements ListUsersUseCase {

    public final UserService userService;
    private final Mapper<UserEntity,UserDto> userMapper;
    private final PageMapper<UserDto> pageMapper;

    public ListUsersUseCaseImpl(UserService userService, Mapper<UserEntity, UserDto> userMapper, PageMapper<UserDto> pageMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.pageMapper = pageMapper;
    }

    private static final Logger logger = LoggerFactory.getLogger(ListUsersUseCaseImpl.class);
    @Override
    public PaginationResponse<UserDto> execute(Pageable pageable) {
        logger.info("Start fetching data");
        Page<UserEntity> allUsers = userService.findAllUsers(pageable);
        logger.info("Data length is: " + allUsers.toList().size());
        Page<UserDto> userDtoPage = allUsers.map(userMapper::mapTO);
        return pageMapper.convert(userDtoPage);
    }
}
