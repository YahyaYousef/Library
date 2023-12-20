package com.example.demo.usecaseimpl.favourite;

import com.example.demo.customexception.InvalidRequestException;
import com.example.demo.customexception.NotFoundException;
import com.example.demo.domain.dto.FavouriteDto;
import com.example.demo.domain.entities.BookEntity;
import com.example.demo.domain.entities.FavouriteEntity;
import com.example.demo.domain.entities.UserEntity;
import com.example.demo.domain.request.FavouriteRequestBody;
import com.example.demo.mapper.impl.FavouriteMapper;
import com.example.demo.services.BookService;
import com.example.demo.services.FavouriteService;
import com.example.demo.services.UserService;
import com.example.demo.usecase.favourite.AddFavouriteUseCase;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Transactional
@Component
public class AddFavouriteUseCaseImpl implements AddFavouriteUseCase {

    private final FavouriteService favouriteService;
    private final BookService bookService;
    private final UserService userService;
    private final FavouriteMapper favouriteMapper;

    public AddFavouriteUseCaseImpl(FavouriteService favouriteService, BookService bookService, UserService userService, FavouriteMapper favouriteMapper) {
        this.favouriteService = favouriteService;
        this.bookService = bookService;
        this.userService = userService;
        this.favouriteMapper = favouriteMapper;
    }

    @Override
    public FavouriteDto execute(FavouriteRequestBody favouriteRequestBody) {
        validateFieldsIsNotEmpty(favouriteRequestBody);
        FavouriteEntity favouriteEntity = convertFavouriteRequestToEntity(favouriteRequestBody);
        Optional<FavouriteEntity> bookInFavourite = favouriteService.isBookInFavourite(favouriteRequestBody.getBookId(), favouriteRequestBody.getUserId());
        if(bookInFavourite.isPresent()){
            throw new InvalidRequestException("This book is already in the favourite");
        }
        favouriteEntity.setId(favouriteService.addToFavourite(favouriteEntity).getId());
        return favouriteMapper.mapTO(favouriteEntity);
    }

    private FavouriteEntity convertFavouriteRequestToEntity(FavouriteRequestBody favouriteRequestBody) {
        Optional<BookEntity> bookEntity = bookService.readOneBook(favouriteRequestBody.getBookId());
        Optional<UserEntity> userEntity = userService.findUserById(favouriteRequestBody.getUserId());
        if (bookEntity.isEmpty()) {
            throw new NotFoundException(("Book is not found"));
        }
        if (userEntity.isEmpty()) {
            throw new NotFoundException(("User is not found"));
        }
        return FavouriteEntity.builder()
                .book(bookEntity.get())
                .user(userEntity.get())
                .build();
    }

    private void validateFieldsIsNotEmpty(FavouriteRequestBody favouriteRequestBody) throws InvalidRequestException, NotFoundException {
        if (favouriteRequestBody.getBookId() == null) {
            throw new InvalidRequestException("Book Id is empty!");
        }
        if (favouriteRequestBody.getUserId() == null) {
            throw new InvalidRequestException("User Id is empty!");
        }
    }
}
