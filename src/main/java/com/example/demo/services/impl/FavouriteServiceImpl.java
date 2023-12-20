package com.example.demo.services.impl;

import com.example.demo.domain.entities.FavouriteEntity;
import com.example.demo.repo.FavouriteRepo;
import com.example.demo.services.FavouriteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FavouriteServiceImpl implements FavouriteService {

    private final FavouriteRepo favouriteRepo;

    public FavouriteServiceImpl(FavouriteRepo favouriteRepo) {
        this.favouriteRepo = favouriteRepo;
    }

    @Override
    public FavouriteEntity addToFavourite(FavouriteEntity favouriteEntity) {
        return favouriteRepo.save(favouriteEntity);
    }

    @Override
    public Optional<FavouriteEntity> isBookInFavourite(Long bookId, UUID userId) {
        return favouriteRepo.findByBookIdAndUserId(bookId,userId);
    }

    @Override
    public void removeBookFromFavourite(FavouriteEntity favouriteEntity) {
        favouriteRepo.delete(favouriteEntity);
    }

    @Override
    public Page<FavouriteEntity> getFavouritesBooksByUserId(Pageable pageable,UUID userId) {
        return favouriteRepo.findAllFavouritesByUserId(pageable,userId);
    }


}
