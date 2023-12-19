package com.example.demo.mapper.impl;

import com.example.demo.domain.dto.BookDto;
import com.example.demo.domain.entities.BookEntity;
import com.example.demo.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements Mapper<BookEntity, BookDto> {


    private final ModelMapper mapper;

    public BookMapperImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public BookDto mapTO(BookEntity bookEntity) {
        return mapper.map(bookEntity, BookDto.class);
    }

    @Override
    public BookEntity mapFrom(BookDto bookDto) {
        return mapper.map(bookDto, BookEntity.class);
    }
}

