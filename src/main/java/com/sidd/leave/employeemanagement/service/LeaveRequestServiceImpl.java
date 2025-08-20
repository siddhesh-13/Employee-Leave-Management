package com.sidd.leave.employeemanagement.service;

import com.sidd.leave.employeemanagement.entity.LeaveRequest;
import com.sidd.leave.employeemanagement.entity.User;
import com.sidd.leave.employeemanagement.enums.LeaveType;
import com.sidd.leave.employeemanagement.repository.LeaveRequestRepository;
import com.sidd.leave.employeemanagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class LeaveRequestServiceImpl implements LeaveRequestService{

    private LeaveRequestRepository leaveRequestRepository;
    private UserRepository userRepository;

    @Override
    public LeaveRequest applyLeave(User user, LeaveRequest leaveRequest) {
//        User user= userRepository.findById(userId).orElseThrow();

//        leaveRequest.setStartDate(LocalDate.now());
//        leaveRequest.setEndDate(LocalDate.now().plusDays(2));

        leaveRequest.setRequestedBy(user);

        LeaveRequest newLeave= leaveRequestRepository.save(leaveRequest);

        return newLeave;

    }
}
