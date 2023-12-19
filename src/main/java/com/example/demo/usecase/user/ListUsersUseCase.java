package com.example.demo.usecase.user;

import com.example.demo.domain.dto.UserDto;
import com.example.demo.usecase.UseCase;

import java.util.List;

public interface ListUsersUseCase extends UseCase<Object, List<UserDto>> {
}
