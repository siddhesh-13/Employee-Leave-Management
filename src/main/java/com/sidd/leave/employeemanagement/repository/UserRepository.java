package com.sidd.leave.employeemanagement.repository;

import com.sidd.leave.employeemanagement.dto.UserRequestDto;
import com.sidd.leave.employeemanagement.dto.UserResponseDto;
import com.sidd.leave.employeemanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

}
