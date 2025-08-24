package com.sidd.leave.employeemanagement.dto;

import com.sidd.leave.employeemanagement.entity.User;
import com.sidd.leave.employeemanagement.enums.LeaveType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
public class LeaveBalanceDto {
    private Long id;

    private int totalBalance;

    private int remainingBalance;

    private LeaveType leaveType;

    private Long userId;

}
