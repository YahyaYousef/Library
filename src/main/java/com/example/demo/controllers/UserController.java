package com.example.demo.controllers;

import com.example.demo.domain.dto.UserDto;
import com.example.demo.domain.request.ImageUploadRequest;
import com.example.demo.domain.request.UserRequestBody;
import com.example.demo.usecase.user.*;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@Log
public class UserController {

   public final CreateUserUseCase createUser;
   public final ListUsersUseCase listUsers;
   public final ListUserByIdUseCase listUserById;
   public final UploadProfileImageUseCase uploadProfileImageUseCase;

   public final GetProfileImageUseCase imageUseCase;


    public UserController(CreateUserUseCase createUser, ListUsersUseCase listUsers, ListUserByIdUseCase listUserById, UploadProfileImageUseCase uploadProfileImageUseCase, GetProfileImageUseCase imageUseCase) {
        this.createUser = createUser;
        this.listUsers = listUsers;
        this.listUserById = listUserById;
        this.uploadProfileImageUseCase = uploadProfileImageUseCase;
        this.imageUseCase = imageUseCase;
    }

    @GetMapping("/users")
    public List<UserDto> listAllUsers(){
        return listUsers.execute(null);
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserRequestBody user) {
        return ResponseEntity.ok(createUser.execute(user));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(listUserById.execute(id));
    }

    @PatchMapping("/user/uploadProfile")
    public ResponseEntity<UserDto> uploadProfileImage(@ModelAttribute ImageUploadRequest request) {
        return ResponseEntity.ok(uploadProfileImageUseCase.execute(request));
    }

    @GetMapping("/user/profile/{imageId}")
    public byte[] getProfileImage(@PathVariable("imageId") UUID imageId){
        return imageUseCase.execute(imageId);
    }

}
