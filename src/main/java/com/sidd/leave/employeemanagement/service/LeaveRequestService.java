package com.sidd.leave.employeemanagement.service;

import com.sidd.leave.employeemanagement.dto.LeaveRequestDto;
import com.sidd.leave.employeemanagement.entity.LeaveRequest;
import com.sidd.leave.employeemanagement.entity.User;

import java.util.List;

public interface LeaveRequestService {
    LeaveRequestDto applyLeave(User user, LeaveRequestDto leaveRequestDto);
//    List<LeaveRequest> findByUserId(Long userId);
    List<LeaveRequestDto> getMyLeaves(Long userId);
}
