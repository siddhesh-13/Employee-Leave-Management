package com.sidd.leave.employeemanagement.service;

import com.sidd.leave.employeemanagement.dto.LeaveRequestDto;
import com.sidd.leave.employeemanagement.entity.LeaveRequest;
import com.sidd.leave.employeemanagement.entity.User;
import com.sidd.leave.employeemanagement.enums.LeaveStatus;

import java.util.List;

public interface LeaveRequestService {
    LeaveRequestDto applyLeave(User user, LeaveRequestDto leaveRequestDto);
//    List<LeaveRequest> findByUserId(Long userId);
    List<LeaveRequestDto> getMyLeaves(Long userId);

    List<LeaveRequestDto> getMyPendingLeaves(Long userId);

//  To get All pending Leaves under the Manager queue
    List<LeaveRequestDto> getPendingLeavesForManager(Long userId);

//    Manager/HR can update the leave status
    LeaveRequestDto updateLeaveStatus(Long userId, Long reqId, LeaveStatus leaveStatus);

//    All leaves assigned to Manager/HR
    List<LeaveRequestDto> getAllLeavesForApprover(Long approverId);

    List<LeaveRequestDto> getAllLeaveForHr(Long hrId);
}
