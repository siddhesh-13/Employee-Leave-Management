package com.sidd.leave.employeemanagement.repository;

import com.sidd.leave.employeemanagement.entity.LeavePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeavePolicyRepository extends JpaRepository<LeavePolicy, Long> {
}
