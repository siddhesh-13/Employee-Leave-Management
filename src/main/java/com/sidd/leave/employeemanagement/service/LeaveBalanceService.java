package com.sidd.leave.employeemanagement.service;

import com.sidd.leave.employeemanagement.dto.LeaveBalanceDto;
import com.sidd.leave.employeemanagement.entity.LeaveBalance;

import java.util.List;

public interface LeaveBalanceService {
    List<LeaveBalanceDto> getLeaveBalanceById(Long userId);
}
