package com.example.demo.usecase.favourite;

import com.example.demo.domain.dto.FavouriteDto;
import com.example.demo.domain.request.FavouriteListRequest;
import com.example.demo.domain.response.PaginationResponse;
import com.example.demo.usecase.UseCase;

public interface GetBooksInFavouritesByUserId extends UseCase<FavouriteListRequest, PaginationResponse<FavouriteDto>> {
}
