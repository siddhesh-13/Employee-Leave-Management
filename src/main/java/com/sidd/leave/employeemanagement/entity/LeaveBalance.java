package com.sidd.leave.employeemanagement.entity;

import com.sidd.leave.employeemanagement.enums.LeaveType;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user", "policy"})
@Entity
@Table(name = "leave_balance",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "leaveType"})
    )
public class LeaveBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int totalBalance;

    @Column(nullable = false)
    private int remainingBalance;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false )
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id", nullable = false)
    private LeavePolicy policy;
}
