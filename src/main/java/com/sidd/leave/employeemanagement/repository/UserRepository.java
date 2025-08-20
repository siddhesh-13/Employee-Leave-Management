package com.sidd.leave.employeemanagement.repository;

import com.sidd.leave.employeemanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
}
