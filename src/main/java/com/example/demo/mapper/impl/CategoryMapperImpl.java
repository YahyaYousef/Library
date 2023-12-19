package com.example.demo.mapper.impl;

import com.example.demo.domain.dto.CategoryDto;
import com.example.demo.domain.entities.CategoryEntity;
import com.example.demo.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperImpl implements Mapper<CategoryEntity, CategoryDto> {


    private final ModelMapper mapper;

    public CategoryMapperImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public CategoryDto mapTO(CategoryEntity CategoryEntity) {
        return mapper.map(CategoryEntity,CategoryDto.class);
    }

    @Override
    public CategoryEntity mapFrom(CategoryDto CategoryDto) {
        return mapper.map(CategoryDto,CategoryEntity.class);
    }
}

