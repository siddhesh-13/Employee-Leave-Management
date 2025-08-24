package com.sidd.leave.employeemanagement.entity;

import com.sidd.leave.employeemanagement.enums.LeaveType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "leave_policy")
public class LeavePolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;

    @Column(nullable = false)
    private int yearlyLimit;

    @Column(nullable = false)
    private boolean carryForward;

    private String specificRule;

    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<LeaveBalance> leaveBalances;
}
