package com.example.demo.services;

import com.example.demo.domain.entities.FavouriteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;


public interface FavouriteService {

    FavouriteEntity addToFavourite(FavouriteEntity favouriteEntity);
    Optional<FavouriteEntity> isBookInFavourite(Long bookId, UUID userId);
    void removeBookFromFavourite(FavouriteEntity favouriteEntity);

    Page<FavouriteEntity> getFavouritesBooksByUserId(Pageable pageable, UUID userId);

}
