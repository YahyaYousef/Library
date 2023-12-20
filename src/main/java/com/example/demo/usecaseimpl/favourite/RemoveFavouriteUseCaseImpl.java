package com.example.demo.usecaseimpl.favourite;

import com.example.demo.customexception.InvalidRequestException;
import com.example.demo.customexception.NotFoundException;
import com.example.demo.domain.entities.FavouriteEntity;
import com.example.demo.domain.request.FavouriteRequestBody;
import com.example.demo.services.FavouriteService;
import com.example.demo.usecase.favourite.RemoveFavouriteUseCase;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Transactional
@Component
public class RemoveFavouriteUseCaseImpl implements RemoveFavouriteUseCase {

    private final FavouriteService favouriteService;


    public RemoveFavouriteUseCaseImpl(FavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }

    @Override
    public String execute(FavouriteRequestBody favouriteRequestBody) {
        validateFieldsIsNotEmpty(favouriteRequestBody);
        FavouriteEntity favouriteEntity = findFavouriteEntity(favouriteRequestBody);
        favouriteService.removeBookFromFavourite(favouriteEntity);
        return "Success";
    }

    private FavouriteEntity findFavouriteEntity(FavouriteRequestBody favouriteRequestBody) {
        Optional<FavouriteEntity> bookInFavourite = favouriteService.isBookInFavourite(favouriteRequestBody.getBookId(), favouriteRequestBody.getUserId());
        if(bookInFavourite.isPresent()){
            return bookInFavourite.get();
        }
        throw new NotFoundException("This book not found in the favourite list");
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
