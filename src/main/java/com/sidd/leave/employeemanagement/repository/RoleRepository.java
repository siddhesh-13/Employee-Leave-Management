package com.sidd.leave.employeemanagement.repository;

import com.sidd.leave.employeemanagement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
