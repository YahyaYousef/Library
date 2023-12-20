package com.example.demo.usecase.favourite;

import com.example.demo.domain.request.FavouriteRequestBody;
import com.example.demo.usecase.UseCase;

public interface IsBookExistInFavouriteUseCase extends UseCase<FavouriteRequestBody, Boolean> {
}
