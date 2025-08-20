package com.sidd.leave.employeemanagement.entity;

import com.sidd.leave.employeemanagement.enums.LeaveStatus;
import com.sidd.leave.employeemanagement.enums.LeaveType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "leave_requests")
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private LeaveStatus leaveStatus;

    private LocalDateTime appliedAt;

    @PrePersist
    protected void onCreate(){
        if (leaveStatus==null){
            leaveStatus=LeaveStatus.PENDING;
        }
        if (appliedAt==null){
            this.appliedAt=LocalDateTime.now();
        }
    }

//    save file path/url
    @Column(name = "file_path")
    private String filePath;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "requested_by", nullable = false)
    private User requestedBy;       //leave requested by

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "approver_id")
    private User approver;        //leave approved/rejected by
}
