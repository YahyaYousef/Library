package com.example.demo.usecase.user;

import com.example.demo.domain.dto.UserDto;
import com.example.demo.domain.response.PaginationResponse;
import com.example.demo.usecase.UseCase;
import org.springframework.data.domain.Pageable;


public interface ListUsersUseCase extends UseCase<Pageable, PaginationResponse<UserDto>> {
}
