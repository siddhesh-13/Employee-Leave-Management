package com.sidd.leave.employeemanagement.controller;

import com.sidd.leave.employeemanagement.dto.AssignManagerRequest;
import com.sidd.leave.employeemanagement.dto.UserRequestDto;
import com.sidd.leave.employeemanagement.dto.UserResponseDto;
import com.sidd.leave.employeemanagement.entity.Role;
import com.sidd.leave.employeemanagement.enums.UserStatus;
import com.sidd.leave.employeemanagement.service.UserService;
import com.sidd.leave.employeemanagement.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserTestController {

    private UserServiceImpl userService;

//  New User registeration
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRequestDto userRequestDtoDto){
        UserResponseDto userResponseDto= userService.registerUser(userRequestDtoDto);
        return ResponseEntity.ok(userResponseDto);
    }


//    User Profile on Login
    @GetMapping("/profile")
    public ResponseEntity<String> profile(){
        return ResponseEntity.ok("Welcome User");
    }


//    Get All Users with Pending Status
    @GetMapping("/pending")
    public ResponseEntity<List<UserResponseDto>> pendingUsers(){
        List<UserResponseDto> userResponseDtos = userService.getAllPendingUsers();
        return ResponseEntity.ok(userResponseDtos);
    }

//    Assign Role to Users and Update their status
    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDto> updateStatusAndAssignRole(@PathVariable Long id, @RequestBody Map<String, String> request){

        String roleName=request.get("roleName");
        String userStatus=request.get("status");


        UserResponseDto userResponseDto= userService.updateUser(id, UserStatus.valueOf(userStatus), roleName);

        return ResponseEntity.ok(userResponseDto);
    }

    @PutMapping("/{userId}/assign-manager")
    public ResponseEntity<String> assignManager(@PathVariable Long userId, @RequestBody AssignManagerRequest request){

        userService.assignManager(userId, request.getManagerId());

        return ResponseEntity.ok("Manager assigned");
    }

//    Get All Managers mapped to HR
    @GetMapping("/hr/{hrId}/managers")
    public ResponseEntity<List<UserResponseDto>> getAllManagersUnderHR(@PathVariable Long hrId){
        List<UserResponseDto> userResponseDtos = userService.getAllManagersUnderHr(hrId);

        return ResponseEntity.ok(userResponseDtos);
    }

    //    Get All Employees mapped to HR
    @GetMapping("/hr/{hrId}/employees")
    public ResponseEntity<List<UserResponseDto>> getAllEmployeesUnderHR(@PathVariable Long hrId){
        List<UserResponseDto> userResponseDtos = userService.getAllEmployeesUnderHr(hrId);

        return ResponseEntity.ok(userResponseDtos);
    }
}
