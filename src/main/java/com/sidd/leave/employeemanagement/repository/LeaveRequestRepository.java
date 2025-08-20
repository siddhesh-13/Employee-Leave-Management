package com.sidd.leave.employeemanagement.repository;

import com.sidd.leave.employeemanagement.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
}
