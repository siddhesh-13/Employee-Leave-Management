package com.sidd.leave.employeemanagement.service;

import com.sidd.leave.employeemanagement.entity.LeaveRequest;
import com.sidd.leave.employeemanagement.entity.User;

public interface LeaveRequestService {
    LeaveRequest applyLeave(User user, LeaveRequest leaveRequest);
}
