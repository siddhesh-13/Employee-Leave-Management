package com.sidd.leave.employeemanagement.repository;

import com.sidd.leave.employeemanagement.dto.LeaveRequestDto;
import com.sidd.leave.employeemanagement.entity.LeaveRequest;
import com.sidd.leave.employeemanagement.enums.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByRequestedBy_Id(Long userId);
    List<LeaveRequest> findByRequestedBy_IdAndLeaveStatus(Long userId, LeaveStatus leaveStatus);
    List<LeaveRequest> findByApprover_IdAndLeaveStatus(Long userId, LeaveStatus leaveStatus);
    List<LeaveRequest> findByApprover_Id(Long managerId);

//    Employee leaves under HR's managerss
    @Query("SELECT l FROM LeaveRequest l WHERE l.approver.hr.id= :hrId")
    List<LeaveRequest> findAllByHr(@Param("hrId") Long hrId);
}
