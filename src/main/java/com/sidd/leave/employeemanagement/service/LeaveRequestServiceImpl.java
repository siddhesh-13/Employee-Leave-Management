package com.sidd.leave.employeemanagement.service;

import com.sidd.leave.employeemanagement.config.ModelMapperConfig;
import com.sidd.leave.employeemanagement.dto.LeaveRequestDto;
import com.sidd.leave.employeemanagement.entity.LeaveBalance;
import com.sidd.leave.employeemanagement.entity.LeaveRequest;
import com.sidd.leave.employeemanagement.entity.User;
import com.sidd.leave.employeemanagement.enums.LeaveStatus;
import com.sidd.leave.employeemanagement.repository.LeaveBalanceRepository;
import com.sidd.leave.employeemanagement.repository.LeaveRequestRepository;
import com.sidd.leave.employeemanagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
    private LeaveBalanceRepository leaveBalanceRepository;

    @Override
    public LeaveRequestDto applyLeave(User user, LeaveRequestDto leaveRequestDto) {

        LeaveRequest leaveRequest=modelMapper.map(leaveRequestDto, LeaveRequest.class);

        LeaveBalance leaveBalance = leaveBalanceRepository.findByUser_IdAndLeaveType(user.getId(), leaveRequest.getLeaveType());

//      Total days of leave applied by user
        int leaveDays= (int) ChronoUnit.DAYS.between(leaveRequest.getStartDate(),leaveRequest.getEndDate()) +1; // +1 to include both dates

        if (leaveRequest.getStartDate().isBefore(LocalDate.now()) ){
            throw new IllegalArgumentException("Cannot apply leave for past date");
        }

        if (leaveRequest.getStartDate().isAfter(LocalDate.now().plusMonths(3))){
            throw new IllegalArgumentException("Cannot apply leave more than 3 months in advance");
        }

        if (leaveRequest.getEndDate().isBefore(leaveRequest.getStartDate())){
            throw  new IllegalArgumentException("Invalid leave request, please check dates");
        }

        if (leaveBalance.getRemainingBalance() >= leaveDays){
            leaveRequest.setRequestedBy(user);

            if (user.getManager()!=null){
                leaveRequest.setApprover(user.getManager());
            } else if(user.getHr()!=null) {
                leaveRequest.setApprover(user.getHr());
            }else {
                throw new RuntimeException("Approver doesn't exists for this employee");
            }

            leaveRequestRepository.save(leaveRequest);

//            leaveBalance.setRemainingBalance(leaveBalance.getRemainingBalance()-leaveDays);
//
//            leaveBalanceRepository.save(leaveBalance);

        }
        else {
            throw new RuntimeException("Insufficient Leave Balance");
        }


        return modelMapper.map(leaveRequest, LeaveRequestDto.class);

    }

    @Override
    public List<LeaveRequestDto> getMyLeaves(Long userId) {

        List<LeaveRequest> leaveRequests= leaveRequestRepository.findByRequestedBy_Id(userId);

        return leaveRequests.stream()
                .map(leaveRequest -> modelMapper.map(leaveRequest, LeaveRequestDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<LeaveRequestDto> getMyPendingLeaves(Long userId) {
        List<LeaveRequest> leaveRequests= leaveRequestRepository.findByRequestedBy_IdAndLeaveStatus(userId, LeaveStatus.PENDING);

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

        if (leaveRequest.getLeaveStatus().equals(LeaveStatus.PENDING)) {

            if (leaveStatus.equals(LeaveStatus.APPROVED)) {
                leaveRequest.setLeaveStatus(LeaveStatus.APPROVED);
                int leaveDays = (int) ChronoUnit.DAYS.between(leaveRequest.getStartDate(), leaveRequest.getEndDate()) + 1;
                User user = leaveRequest.getRequestedBy();
                LeaveBalance leaveBalance = leaveBalanceRepository.findByUser_IdAndLeaveType(user.getId(), leaveRequest.getLeaveType());
                leaveBalance.setRemainingBalance(leaveBalance.getRemainingBalance() - leaveDays);
                leaveBalanceRepository.save(leaveBalance);
            } else if (leaveStatus.equals(LeaveStatus.REJECTED)) {
                leaveRequest.setLeaveStatus(LeaveStatus.REJECTED);
            } else {
                throw new RuntimeException("Invalid action");
            }

        }
        else {
            throw new RuntimeException("Leave status already updated");
        }
        return modelMapper.map( leaveRequestRepository.save(leaveRequest), LeaveRequestDto.class);

    }

    @Override
    public List<LeaveRequestDto> getAllLeavesForApprover(Long approverId) {
        List<LeaveRequest> leaveRequests = leaveRequestRepository.findByApprover_Id(approverId);

        return leaveRequests.stream()
                .map(leaveRequest -> modelMapper.map(leaveRequest, LeaveRequestDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<LeaveRequestDto> getAllLeaveForHr(Long hrId) {
        List<LeaveRequest> leaveRequests= leaveRequestRepository.findAllByHr(hrId);

        return leaveRequests.stream()
                .map(leaveRequest -> modelMapper.map(leaveRequest, LeaveRequestDto.class))
                .collect(Collectors.toList());
    }


}
