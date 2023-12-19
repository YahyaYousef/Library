package com.example.demo.usecase.user;

import com.example.demo.domain.dto.UserDto;
import com.example.demo.domain.request.UserRequestBody;
import com.example.demo.usecase.UseCase;

public interface CreateUserUseCase extends UseCase<UserRequestBody, UserDto> {
}
