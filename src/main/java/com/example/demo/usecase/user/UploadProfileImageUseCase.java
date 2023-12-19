package com.example.demo.usecase.user;

import com.example.demo.domain.dto.UserDto;
import com.example.demo.domain.request.ImageUploadRequest;
import com.example.demo.usecase.UseCase;

public interface UploadProfileImageUseCase extends UseCase<ImageUploadRequest, UserDto> {
}
