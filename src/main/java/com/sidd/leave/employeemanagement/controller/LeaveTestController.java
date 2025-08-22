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


//  -------------  TO APPLY LEAVE  ---------------
    @PostMapping("/apply/{userId}")
    public ResponseEntity<LeaveRequestDto> applyLeaveReq(@PathVariable Long userId, @RequestBody LeaveRequestDto leaveRequestDto){

        User user=userRepository.findById(userId).orElseThrow();

        LeaveRequestDto newLeave= leaveRequestService.applyLeave(user,leaveRequestDto);

        return ResponseEntity.ok(newLeave);

    }

//    -------------  TO GET ALL LEAVE REQUESTS FOR AN EMPLOYEE  ---------------
    @GetMapping("/requests/{userId}")
    public ResponseEntity<List<LeaveRequestDto>> getMyLeaveRequests(@PathVariable Long userId){

        List<LeaveRequestDto> leaveRequestDtos= leaveRequestService.getMyLeaves(userId);

        return ResponseEntity.ok(leaveRequestDtos);
    }

//    -------------  GET ALL PENDING REQUESTS IN QUEUE FOR MANAGER  ---------------
    @GetMapping("/pending/{userId}")
    public ResponseEntity<List<LeaveRequestDto>> getPendingLeaveForManager(@PathVariable Long userId){
        List<LeaveRequestDto> leaveRequestDtos = leaveRequestService.getPendingLeavesForManager(userId);

        return ResponseEntity.ok(leaveRequestDtos);
    }

//    -------------  TO UPDATE THE LEAVE REQUEST BY MANAGER  ---------------
    @PostMapping("/update/{reqId}/action")
    public ResponseEntity<LeaveRequestDto> updateLeaveStatus(@PathVariable Long reqId,
                                                             @RequestBody LeaveStatus leaveStatus,
                                                             @RequestParam Long userId){

        LeaveRequestDto leaveRequestDto= leaveRequestService.updateLeaveStatus(userId, reqId, leaveStatus);

        return ResponseEntity.ok(leaveRequestDto);
    }


//    -------------  TO GET ALL REQUEST ASSIGNED TO MANAGER  ---------------
    @GetMapping("/manager/{managerId}")
    public ResponseEntity<List<LeaveRequestDto>> getAllLeavesAssignedToManager(@PathVariable Long managerId){

        List<LeaveRequestDto> leaveRequestDtos= leaveRequestService.getAllLeavesForManager(managerId);

        return ResponseEntity.ok(leaveRequestDtos);
    }

}
