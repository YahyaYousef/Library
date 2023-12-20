package com.example.demo.controllers;

import com.example.demo.domain.dto.FavouriteDto;
import com.example.demo.domain.entities.UserEntity;
import com.example.demo.domain.request.FavouriteListRequest;
import com.example.demo.domain.request.FavouriteRequestBody;
import com.example.demo.domain.response.PaginationResponse;
import com.example.demo.usecase.favourite.AddFavouriteUseCase;
import com.example.demo.usecase.favourite.GetBooksInFavouritesByUserId;
import com.example.demo.usecase.favourite.IsBookExistInFavouriteUseCase;
import com.example.demo.usecase.favourite.RemoveFavouriteUseCase;
import lombok.extern.java.Log;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@Log
public class FavouriteController {

    private final AddFavouriteUseCase addFavouriteUseCase;
    private final RemoveFavouriteUseCase removeFavouriteUseCase;
    private final IsBookExistInFavouriteUseCase isBookExistInFavouriteUseCase;
    private final GetBooksInFavouritesByUserId getBooksInFavouritesByUserId;

    public FavouriteController(AddFavouriteUseCase addFavouriteUseCase, RemoveFavouriteUseCase removeFavouriteUseCase, IsBookExistInFavouriteUseCase isBookExistInFavouriteUseCase, GetBooksInFavouritesByUserId getBooksInFavouritesByUserId) {
        this.addFavouriteUseCase = addFavouriteUseCase;
        this.removeFavouriteUseCase = removeFavouriteUseCase;
        this.isBookExistInFavouriteUseCase = isBookExistInFavouriteUseCase;
        this.getBooksInFavouritesByUserId = getBooksInFavouritesByUserId;
    }

    @PostMapping("/favourite/{bookId}")
    public ResponseEntity<FavouriteDto> addBookToFavourite(@PathVariable Long bookId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();
        FavouriteDto favouriteDto = addFavouriteUseCase.execute(FavouriteRequestBody.builder()
                .bookId(bookId)
                .userId(user.getId())
                .build());
        return ResponseEntity.status(HttpStatus.CREATED).body(favouriteDto);
    }


    @DeleteMapping("/favourite/{bookId}")
    public ResponseEntity<String> removeBookFromFavourite(@PathVariable Long bookId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();
        FavouriteRequestBody favouriteRequestBody = FavouriteRequestBody.builder()
                .bookId(bookId)
                .userId(user.getId())
                .build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(removeFavouriteUseCase.execute(favouriteRequestBody));
    }

    @GetMapping("/favourite/{bookId}/exist")
    public ResponseEntity<Boolean> isBookExistInFavourite(@PathVariable Long bookId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();
        FavouriteRequestBody favouriteRequestBody = FavouriteRequestBody.builder()
                .bookId(bookId)
                .userId(user.getId())
                .build();
        return ResponseEntity.ok(isBookExistInFavouriteUseCase.execute(favouriteRequestBody));
    }

    @GetMapping("/favourite")
    public PaginationResponse<FavouriteDto> getMyBooksInFavourite(@RequestParam(required = false,defaultValue = "${pageSettings.defaultPageNumber}") int page,
                                                                     @RequestParam(required = false,defaultValue = "${pageSettings.defaultPageSize}") int size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();
        Pageable pageable = PageRequest.of(page, size);
        FavouriteListRequest request = FavouriteListRequest.builder()
                .userId(user.getId())
                .pageable(pageable)
                .build();
        return getBooksInFavouritesByUserId.execute(request);
    }

}
