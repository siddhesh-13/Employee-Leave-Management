package com.sidd.leave.employeemanagement.repository;

import com.sidd.leave.employeemanagement.entity.LeaveBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance, Long> {

    List<LeaveBalance> findByUser_Id(Long userId);
}
