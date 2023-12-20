package com.example.demo.repo;

import com.example.demo.domain.entities.FavouriteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FavouriteRepo extends CrudRepository<FavouriteEntity,Long>, PagingAndSortingRepository<FavouriteEntity,Long> {

    Page<FavouriteEntity> findAllFavouritesByUserId(Pageable pageable, UUID userId);

    Optional<FavouriteEntity> findByBookIdAndUserId(Long bookId,UUID userId);
}
