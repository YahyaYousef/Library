package com.example.demo.usecase.favourite;

import com.example.demo.domain.dto.FavouriteDto;
import com.example.demo.domain.request.FavouriteRequestBody;
import com.example.demo.usecase.UseCase;

public interface AddFavouriteUseCase extends UseCase<FavouriteRequestBody, FavouriteDto> {
}
