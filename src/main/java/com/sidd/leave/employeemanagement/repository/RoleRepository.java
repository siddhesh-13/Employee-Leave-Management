package com.sidd.leave.employeemanagement.repository;

import com.sidd.leave.employeemanagement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    boolean existsByRoleName(String roleName);
    Optional<Role> findByRoleName(String roleName);
}
