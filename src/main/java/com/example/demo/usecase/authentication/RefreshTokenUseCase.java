package com.example.demo.usecase.authentication;

import com.example.demo.domain.request.JwtRequest;
import com.example.demo.domain.response.JwtResponse;
import com.example.demo.usecase.UseCase;

public interface RefreshTokenUseCase extends UseCase<String, JwtResponse> {
}
