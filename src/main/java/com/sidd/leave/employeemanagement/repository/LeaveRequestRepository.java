package com.sidd.leave.employeemanagement.repository;

import com.sidd.leave.employeemanagement.dto.LeaveRequestDto;
import com.sidd.leave.employeemanagement.entity.LeaveRequest;
import com.sidd.leave.employeemanagement.enums.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByRequestedBy_Id(Long userId);
    List<LeaveRequest> findByApprover_IdAndLeaveStatus(Long userId, LeaveStatus leaveStatus);
    List<LeaveRequest> findByApprover_Id(Long managerId);
}
