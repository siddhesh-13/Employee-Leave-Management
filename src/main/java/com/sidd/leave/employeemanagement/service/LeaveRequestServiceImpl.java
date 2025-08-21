package com.sidd.leave.employeemanagement.service;

import com.sidd.leave.employeemanagement.config.ModelMapperConfig;
import com.sidd.leave.employeemanagement.dto.LeaveRequestDto;
import com.sidd.leave.employeemanagement.entity.LeaveRequest;
import com.sidd.leave.employeemanagement.entity.User;
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
    public LeaveRequest applyLeave(User user, LeaveRequest leaveRequest) {
//        User user= userRepository.findById(userId).orElseThrow();

//        leaveRequest.setStartDate(LocalDate.now());
//        leaveRequest.setEndDate(LocalDate.now().plusDays(2));

        leaveRequest.setRequestedBy(user);

        LeaveRequest newLeave= leaveRequestRepository.save(leaveRequest);

        return newLeave;

    }

    @Override
    public List<LeaveRequestDto> getMyLeaves(Long userId) {

        List<LeaveRequest> leaveRequests= leaveRequestRepository.findByRequestedBy_Id(userId);

        return leaveRequests.stream()
                .map(leaveRequest -> modelMapper.map(leaveRequest, LeaveRequestDto.class))
                .collect(Collectors.toList());

    }
}
