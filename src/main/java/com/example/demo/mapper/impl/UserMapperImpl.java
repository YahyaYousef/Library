package com.example.demo.mapper.impl;

import com.example.demo.domain.dto.UserDto;
import com.example.demo.domain.entities.UserEntity;
import com.example.demo.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements Mapper<UserEntity, UserDto> {

    ModelMapper mapper;

    public UserMapperImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public UserDto mapTO(UserEntity user) {
        return mapper.map(user,UserDto.class);
    }

    @Override
    public UserEntity mapFrom(UserDto userDto) {
        return mapper.map(userDto,UserEntity.class);
    }
}
