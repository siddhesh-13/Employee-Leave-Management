package com.sidd.leave.employeemanagement.repository;

import com.sidd.leave.employeemanagement.dto.UserRequestDto;
import com.sidd.leave.employeemanagement.dto.UserResponseDto;
import com.sidd.leave.employeemanagement.entity.Role;
import com.sidd.leave.employeemanagement.entity.User;
import com.sidd.leave.employeemanagement.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    List<User> findByUserStatus(UserStatus userStatus);

// Get All managers or employees under HR
    List<User> findByHrIdAndRoleId(Long hrId, int roleId);

    // Get All employees under manager
    List<User> findByManagerId(Long managerId);

}
