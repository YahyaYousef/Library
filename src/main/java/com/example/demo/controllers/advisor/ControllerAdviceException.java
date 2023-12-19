package com.example.demo.controllers.advisor;

import com.example.demo.customexception.GenericException;
import com.example.demo.customexception.InvalidRequestException;
import com.example.demo.customexception.NotFoundException;
import com.example.demo.customexception.user.UserAlreadyExistException;
import com.example.demo.domain.response.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ControllerAdviceException {

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorModel> userAlreadyExistException(final UserAlreadyExistException userAlreadyExistException){
        ErrorModel errorModel = ErrorModel.builder()
                .title("User already exist")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(userAlreadyExistException.getMessage()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorModel);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorModel> notFoundException(final NotFoundException userNotFoundException){
        ErrorModel errorModel = ErrorModel.builder()
                .title("Not found")
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(userNotFoundException.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorModel);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorModel> invalidRequestException(final InvalidRequestException invalidRequestException){
        ErrorModel errorModel = ErrorModel.builder()
                .title("Invalid request")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(invalidRequestException.getMessage()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorModel);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorModel> generalException(GenericException genericException){
        ErrorModel errorModel = ErrorModel.builder()
                .title("Exception")
                .statusCode(HttpStatus.I_AM_A_TEAPOT.value())
                .message(genericException.getMessage()).build();
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(errorModel);
    }

}
