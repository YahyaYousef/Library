package com.example.demo.usecase.user;

import com.example.demo.domain.dto.UserDto;
import com.example.demo.usecase.UseCase;

import java.util.UUID;

public interface ListUserByIdUseCase extends UseCase<UUID, UserDto> {
}
