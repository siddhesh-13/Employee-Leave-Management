package com.sidd.leave.employeemanagement.controller;

import com.sidd.leave.employeemanagement.dto.UserRequestDto;
import com.sidd.leave.employeemanagement.dto.UserResponseDto;
import com.sidd.leave.employeemanagement.service.UserService;
import com.sidd.leave.employeemanagement.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserTestController {

    private UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRequestDto userRequestDtoDto){
        UserResponseDto userResponseDto= userService.registerUser(userRequestDtoDto);
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/profile")
    public ResponseEntity<String> profile(){
        return ResponseEntity.ok("Welcome User");
    }

    @GetMapping("/pending")
    public ResponseEntity<List<UserResponseDto>> pendingUsers(){
        List<UserResponseDto> userResponseDtos = userService.getAllPendingUsers();
        return ResponseEntity.ok(userResponseDtos);
    }


}
