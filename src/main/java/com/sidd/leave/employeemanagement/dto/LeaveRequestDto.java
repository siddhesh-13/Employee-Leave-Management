package com.sidd.leave.employeemanagement.dto;

import com.sidd.leave.employeemanagement.enums.LeaveStatus;
import com.sidd.leave.employeemanagement.enums.LeaveType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class LeaveRequestDto {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveType leaveType;
    private String description;
    private LeaveStatus leaveStatus;
    private LocalDateTime appliedAt;
}
