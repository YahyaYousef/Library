package com.example.demo.usecaseimpl.favourite;

import com.example.demo.customexception.InvalidRequestException;
import com.example.demo.customexception.NotFoundException;
import com.example.demo.domain.entities.FavouriteEntity;
import com.example.demo.domain.request.FavouriteRequestBody;
import com.example.demo.services.FavouriteService;
import com.example.demo.usecase.favourite.IsBookExistInFavouriteUseCase;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Transactional
@Component
public class IsBookExistInFavouriteUseCaseImpl implements IsBookExistInFavouriteUseCase {

    private final FavouriteService favouriteService;


    public IsBookExistInFavouriteUseCaseImpl(FavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }

    @Override
    public Boolean execute(FavouriteRequestBody favouriteRequestBody) {
        validateFieldsIsNotEmpty(favouriteRequestBody);
        Optional<FavouriteEntity> bookInFavourite = favouriteService.isBookInFavourite(favouriteRequestBody.getBookId(), favouriteRequestBody.getUserId());
        return bookInFavourite.isPresent();
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
