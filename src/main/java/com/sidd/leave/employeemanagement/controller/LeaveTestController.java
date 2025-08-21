package com.sidd.leave.employeemanagement.controller;

import com.sidd.leave.employeemanagement.dto.LeaveRequestDto;
import com.sidd.leave.employeemanagement.entity.LeaveRequest;
import com.sidd.leave.employeemanagement.entity.User;
import com.sidd.leave.employeemanagement.enums.LeaveStatus;
import com.sidd.leave.employeemanagement.repository.UserRepository;
import com.sidd.leave.employeemanagement.service.LeaveRequestServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/test-leave")
public class LeaveTestController {

    private LeaveRequestServiceImpl leaveRequestService;
    private UserRepository userRepository;

    @PostMapping("/apply/{userId}")
    public ResponseEntity<LeaveRequestDto> applyLeaveReq(@PathVariable Long userId, @RequestBody LeaveRequestDto leaveRequestDto){

        User user=userRepository.findById(userId).orElseThrow();

        LeaveRequestDto newLeave= leaveRequestService.applyLeave(user,leaveRequestDto);

        return ResponseEntity.ok(newLeave);

    }

    @GetMapping("/requests/{userId}")
    public ResponseEntity<List<LeaveRequestDto>> getMyLeaveRequests(@PathVariable Long userId){

        List<LeaveRequestDto> leaveRequestDtos= leaveRequestService.getMyLeaves(userId);

        return ResponseEntity.ok(leaveRequestDtos);
    }

    @GetMapping("/pending/{userId}")
    public ResponseEntity<List<LeaveRequestDto>> getPendingLeaveForManager(@PathVariable Long userId){
        List<LeaveRequestDto> leaveRequestDtos = leaveRequestService.getPendingLeavesForManager(userId);

        return ResponseEntity.ok(leaveRequestDtos);
    }

    @PostMapping("/update/{reqId}/action")
    public ResponseEntity<LeaveRequestDto> updateLeaveStatus(@PathVariable Long reqId,
                                                             @RequestBody LeaveStatus leaveStatus,
                                                             @RequestParam Long userId){

        LeaveRequestDto leaveRequestDto= leaveRequestService.updateLeaveStatus(userId, reqId, leaveStatus);

        return ResponseEntity.ok(leaveRequestDto);
    }
}
