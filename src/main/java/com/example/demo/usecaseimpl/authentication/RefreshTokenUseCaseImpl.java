package com.example.demo.usecaseimpl.authentication;

import com.example.demo.config.security.JwtUtil;
import com.example.demo.domain.entities.UserEntity;
import com.example.demo.domain.request.JwtRequest;
import com.example.demo.domain.response.JwtResponse;
import com.example.demo.services.UserService;
import com.example.demo.usecase.UseCase;
import com.example.demo.usecase.authentication.RefreshTokenUseCase;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Transactional
@Component
public class RefreshTokenUseCaseImpl implements RefreshTokenUseCase {

    private final JwtUtil jwtTokenUtil;
    private final UserService userDetailsService;

    public RefreshTokenUseCaseImpl(JwtUtil jwtTokenUtil, UserService userDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public JwtResponse execute(String refreshToken) {
        if(jwtTokenUtil.validateRefreshTokenToken(refreshToken) && !jwtTokenUtil.isTokenExpired(refreshToken)){
            String username = jwtTokenUtil.getUsernameFromRefreshToken(refreshToken);
            Optional<UserEntity> userByUsername = userDetailsService.findUserByUsername(username);
            if (userByUsername.isPresent()) {
                String newToken = jwtTokenUtil.generateToken(username, userByUsername.get().getId().toString());
                String newRefreshToken = jwtTokenUtil.generateRefreshToken(newToken,username);
                Long tokenExpiryInSeconds = jwtTokenUtil.getTokenExpiry() / 1000;
                jwtTokenUtil.updateAccessToken(jwtTokenUtil.getTokenFromRefreshToken(refreshToken),newToken);
                return JwtResponse.builder()
                        .accessToken(newToken)
                        .refreshToken(newRefreshToken)
                        .expiryInSec(tokenExpiryInSeconds)
                        .userID(userByUsername.get().getId())
                        .build();
            }
        }
        throw new BadCredentialsException("Refresh token is unknown");
    }
}
