package com.example.demo.usecaseimpl.authentication;

import com.example.demo.config.security.JwtUtil;
import com.example.demo.customexception.NotFoundException;
import com.example.demo.domain.entities.UserEntity;
import com.example.demo.domain.request.JwtRequest;
import com.example.demo.domain.response.JwtResponse;
import com.example.demo.services.AuthenticationService;
import com.example.demo.services.UserService;
import com.example.demo.usecase.authentication.LoginUseCase;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Transactional
@Component
public class LoginUseCaseImpl implements LoginUseCase {


    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtTokenUtil;

    private final UserService userDetailsService;

    private final AuthenticationService authenticationService;

    public LoginUseCaseImpl(AuthenticationManager authenticationManager, JwtUtil jwtTokenUtil, UserService userDetailsService, AuthenticationService authenticationService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.authenticationService = authenticationService;
    }

    @Override
    public JwtResponse execute(JwtRequest jwtRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password");
        }

        final UserDetails userDetails = authenticationService.userDetailsService().loadUserByUsername(jwtRequest.getUsername());
        Optional<UserEntity> optionalUser = userDetailsService.findUserByUsername(jwtRequest.getUsername());
        if (optionalUser.isPresent()) {
            final String token = jwtTokenUtil.generateToken(userDetails.getUsername(), optionalUser.get().getId().toString());
            String refreshToken = jwtTokenUtil.generateRefreshToken(token,userDetails.getUsername());
            Long tokenExpiryInSeconds = jwtTokenUtil.getTokenExpiry() / 1000;
            return JwtResponse.builder()
                    .accessToken(token)
                    .refreshToken(refreshToken)
                    .expiryInSec(tokenExpiryInSeconds)
                    .userID(optionalUser.get().getId())
                    .build();
        }

        throw new NotFoundException("User not found");
    }
}
