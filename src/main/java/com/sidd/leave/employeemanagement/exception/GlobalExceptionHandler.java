package com.sidd.leave.employeemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUserNotFoundException(UserNotFoundException userNotFoundException,
                                                                       WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails(
                LocalDateTime.now(),
                userNotFoundException.getMessage(),
                webRequest.getDescription(false),
                "USER_NOT_FOUND"
        );

       return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
