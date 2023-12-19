package com.example.demo.services.impl;

import com.example.demo.domain.entities.TokensEntity;
import com.example.demo.repo.TokenRepo;
import com.example.demo.services.TokenService;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TokensServiceImpl implements TokenService {

    private final TokenRepo tokenRepo;

    public TokensServiceImpl(TokenRepo tokenRepo) {
        this.tokenRepo = tokenRepo;
    }

    @Override
    public TokensEntity addToken(TokensEntity tokensEntity) {
        return tokenRepo.save(tokensEntity);
    }

    @Override
    public TokensEntity updateToken(String oldToken,TokensEntity newToken) {
        removeToken(oldToken);
        return tokenRepo.save(newToken);
    }

    @Override
    public void removeToken(String token) {
        tokenRepo.deleteById(token);
    }

    @Override
    public Boolean isThisTokenExist(String token) {
        Optional<TokensEntity> byId = tokenRepo.findById(token);
        return byId.isPresent();
    }
}
