package com.sidd.leave.employeemanagement.controller;

import com.sidd.leave.employeemanagement.entity.LeaveRequest;
import com.sidd.leave.employeemanagement.entity.User;
import com.sidd.leave.employeemanagement.repository.UserRepository;
import com.sidd.leave.employeemanagement.service.LeaveRequestServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
