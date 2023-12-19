package com.example.demo.services;

import com.example.demo.domain.entities.TokensEntity;

public interface TokenService {

    TokensEntity addToken(TokensEntity tokensEntity);
    TokensEntity updateToken(String oldToken,TokensEntity newToken);
    void removeToken(String token);
    Boolean isThisTokenExist(String token);


}
