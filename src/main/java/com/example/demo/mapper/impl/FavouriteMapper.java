package com.example.demo.mapper.impl;

import com.example.demo.domain.dto.FavouriteDto;
import com.example.demo.domain.entities.FavouriteEntity;
import com.example.demo.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FavouriteMapper implements Mapper<FavouriteEntity, FavouriteDto> {
    private final ModelMapper mapper;

    public FavouriteMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public FavouriteDto mapTO(FavouriteEntity favouriteEntity) {
        return mapper.map(favouriteEntity,FavouriteDto.class);
    }

    @Override
    public FavouriteEntity mapFrom(FavouriteDto favouriteDto) {
        return mapper.map(favouriteDto,FavouriteEntity.class);
    }
}
