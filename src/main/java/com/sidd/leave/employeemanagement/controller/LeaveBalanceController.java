package com.sidd.leave.employeemanagement.controller;

import com.sidd.leave.employeemanagement.dto.LeaveBalanceDto;
import com.sidd.leave.employeemanagement.service.LeaveBalanceServiceImpl;
import com.sidd.leave.employeemanagement.service.LeaveRequestServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/leaveBalance")
public class LeaveBalanceController {

    private LeaveBalanceServiceImpl leaveBalanceService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<LeaveBalanceDto>> getLeaveBalanceById(@PathVariable Long userId){

        List<LeaveBalanceDto> leaveBalanceDtos= leaveBalanceService.getLeaveBalanceById(userId);

        return ResponseEntity.ok(leaveBalanceDtos);
    }
}
