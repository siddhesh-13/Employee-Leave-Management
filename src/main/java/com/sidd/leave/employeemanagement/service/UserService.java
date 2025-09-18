package com.sidd.leave.employeemanagement.service;

import com.sidd.leave.employeemanagement.dto.UserRequestDto;
import com.sidd.leave.employeemanagement.dto.UserResponseDto;
import com.sidd.leave.employeemanagement.entity.Role;
import com.sidd.leave.employeemanagement.enums.UserStatus;

import java.util.List;

public interface UserService {
    UserResponseDto registerUser(UserRequestDto userRequestDto);

//    Get All Pending Users
    List<UserResponseDto> getAllPendingUsers();

//    Assign role and update status
    UserResponseDto updateUser(Long id, UserStatus userStatus, String roleName);

    void assignManager(Long userId, Long managerId);
}
