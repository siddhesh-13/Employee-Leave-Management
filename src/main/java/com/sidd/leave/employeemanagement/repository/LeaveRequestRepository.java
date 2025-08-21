package com.sidd.leave.employeemanagement.repository;

import com.sidd.leave.employeemanagement.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByRequestedBy_Id(Long userId);
}
