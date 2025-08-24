package com.sidd.leave.employeemanagement.service;

import com.sidd.leave.employeemanagement.dto.LeaveBalanceDto;
import com.sidd.leave.employeemanagement.entity.LeaveBalance;
import com.sidd.leave.employeemanagement.repository.LeaveBalanceRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LeaveBalanceServiceImpl implements LeaveBalanceService{

    private LeaveBalanceRepository leaveBalanceRepository;

    private ModelMapper modelMapper;

    @Override
    public List<LeaveBalanceDto> getLeaveBalanceById(Long userId) {
         List<LeaveBalance> leaveBalances= leaveBalanceRepository.findByUser_Id(userId);

         return leaveBalances.stream()
                             .map(leaveBalance -> modelMapper.map(leaveBalance, LeaveBalanceDto.class))
                             .collect(Collectors.toList());
    }
}
