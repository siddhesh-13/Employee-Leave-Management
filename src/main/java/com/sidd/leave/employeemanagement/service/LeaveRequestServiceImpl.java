package com.sidd.leave.employeemanagement.service;

import com.sidd.leave.employeemanagement.config.ModelMapperConfig;
import com.sidd.leave.employeemanagement.dto.LeaveRequestDto;
import com.sidd.leave.employeemanagement.entity.LeaveRequest;
import com.sidd.leave.employeemanagement.entity.User;
import com.sidd.leave.employeemanagement.enums.LeaveStatus;
import com.sidd.leave.employeemanagement.repository.LeaveRequestRepository;
import com.sidd.leave.employeemanagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LeaveRequestServiceImpl implements LeaveRequestService{

    private LeaveRequestRepository leaveRequestRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    public LeaveRequestDto applyLeave(User user, LeaveRequestDto leaveRequestDto) {

        LeaveRequest leaveRequest=modelMapper.map(leaveRequestDto, LeaveRequest.class);

        leaveRequest.setRequestedBy(user);

        if (user.getManager()!=null){
            leaveRequest.setApprover(user.getManager());
        } else if(user.getHr()!=null) {
            leaveRequest.setApprover(user.getHr());
        }else {
            throw new RuntimeException("Approver doesn't exists for this employee");
        }



        LeaveRequest newLeave= leaveRequestRepository.save(leaveRequest);

        return modelMapper.map(newLeave, LeaveRequestDto.class);

    }

    @Override
    public List<LeaveRequestDto> getMyLeaves(Long userId) {

        List<LeaveRequest> leaveRequests= leaveRequestRepository.findByRequestedBy_Id(userId);

        return leaveRequests.stream()
                .map(leaveRequest -> modelMapper.map(leaveRequest, LeaveRequestDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<LeaveRequestDto> getPendingLeavesForManager(Long userId) {
        List<LeaveRequest> leaveRequests= leaveRequestRepository.findByApprover_IdAndLeaveStatus(userId, LeaveStatus.PENDING);

        return leaveRequests.stream()
                .map(leaveRequest -> modelMapper.map(leaveRequest, LeaveRequestDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public LeaveRequestDto updateLeaveStatus(Long userId, Long reqId, LeaveStatus leaveStatus) {
        LeaveRequest leaveRequest= leaveRequestRepository.findById(reqId).orElseThrow();

        if (!leaveRequest.getApprover().getId().equals(userId)){
            throw new RuntimeException("User not allowed to update requests");
        }

        if (leaveStatus.equals(LeaveStatus.APPROVED)){
            leaveRequest.setLeaveStatus(LeaveStatus.APPROVED);
        } else if (leaveStatus.equals(LeaveStatus.REJECTED)){
            leaveRequest.setLeaveStatus(LeaveStatus.REJECTED);
        } else {
            throw new RuntimeException("Invalid action");
        }

        return modelMapper.map( leaveRequestRepository.save(leaveRequest), LeaveRequestDto.class);

    }
}
