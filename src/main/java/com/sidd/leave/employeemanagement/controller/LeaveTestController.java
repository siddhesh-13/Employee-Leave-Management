package com.sidd.leave.employeemanagement.controller;

import com.sidd.leave.employeemanagement.dto.LeaveRequestDto;
import com.sidd.leave.employeemanagement.entity.LeaveRequest;
import com.sidd.leave.employeemanagement.entity.User;
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
    public ResponseEntity<LeaveRequest> applyLeaveReq(@PathVariable Long userId, @RequestBody LeaveRequest leaveRequest){

        User user=userRepository.findById(userId).orElseThrow();

        LeaveRequest newLeave= leaveRequestService.applyLeave(user,leaveRequest);

        return ResponseEntity.ok(newLeave);

    }

    @GetMapping("/requests/{userId}")
    public ResponseEntity<List<LeaveRequestDto>> getMyLeaveRequests(@PathVariable Long userId){

        List<LeaveRequestDto> leaveRequestDtos= leaveRequestService.getMyLeaves(userId);

        return ResponseEntity.ok(leaveRequestDtos);
    }

}
