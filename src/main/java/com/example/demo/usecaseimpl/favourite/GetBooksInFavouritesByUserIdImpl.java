package com.example.demo.usecaseimpl.favourite;

import com.example.demo.config.PageMapper;
import com.example.demo.domain.dto.FavouriteDto;
import com.example.demo.domain.entities.FavouriteEntity;
import com.example.demo.domain.request.FavouriteListRequest;
import com.example.demo.domain.response.PaginationResponse;
import com.example.demo.mapper.impl.FavouriteMapper;
import com.example.demo.services.FavouriteService;
import com.example.demo.usecase.favourite.GetBooksInFavouritesByUserId;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Transactional
@Component
public class GetBooksInFavouritesByUserIdImpl implements GetBooksInFavouritesByUserId {

    private final FavouriteService favouriteService;
    private final FavouriteMapper favouriteMapper;
    private final PageMapper<FavouriteDto> favouriteDtoPageMapper;
    public GetBooksInFavouritesByUserIdImpl(FavouriteService favouriteService, FavouriteMapper favouriteMapper, PageMapper<FavouriteDto> favouriteDtoPageMapper) {
        this.favouriteService = favouriteService;
        this.favouriteMapper = favouriteMapper;
        this.favouriteDtoPageMapper = favouriteDtoPageMapper;
    }

    @Override
    public PaginationResponse<FavouriteDto> execute(FavouriteListRequest favouriteListRequest) {
        Page<FavouriteEntity> favouriteEntities = favouriteService.getFavouritesBooksByUserId(favouriteListRequest.getPageable(), favouriteListRequest.getUserId());
        return favouriteDtoPageMapper.convert(favouriteEntities.map(favouriteMapper::mapTO));
    }
}
