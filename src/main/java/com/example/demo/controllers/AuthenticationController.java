package com.example.demo.controllers;

import com.example.demo.domain.entities.UserEntity;
import com.example.demo.domain.request.JwtRequest;
import com.example.demo.domain.response.JwtResponse;

import com.example.demo.usecase.authentication.LoginUseCase;
import com.example.demo.usecase.authentication.RefreshTokenUseCase;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/auth")
@Log
public class AuthenticationController {

    public final LoginUseCase loginUseCase;
    public final RefreshTokenUseCase refreshTokenUseCase;

    public AuthenticationController(LoginUseCase loginUseCase, RefreshTokenUseCase refreshTokenUseCase) {
        this.loginUseCase = loginUseCase;
        this.refreshTokenUseCase = refreshTokenUseCase;
    }


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) {
      return ResponseEntity.ok(loginUseCase.execute(jwtRequest));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<JwtResponse> refreshToken(@RequestHeader("refreshToken") String refreshToken) {
        return ResponseEntity.ok(refreshTokenUseCase.execute(refreshToken));
    }

}

