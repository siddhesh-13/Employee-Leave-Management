package com.sidd.leave.employeemanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sidd.leave.employeemanagement.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus=UserStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

//    leaves applied by the user
    @ToString.Exclude
    @OneToMany(mappedBy = "requestedBy", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<LeaveRequest> leaveRequests;

//    leaves approved by the user
    @ToString.Exclude
    @OneToMany(mappedBy = "approver")
    @JsonIgnore
    private List<LeaveRequest> reviewedRequests;

    // ---------------- Hierarchy Mappings ----------------

    // Employee → Manager
    @ManyToOne
    @JoinColumn(name = "manager_id")
    @ToString.Exclude
    private User manager; // The manager of this employee

    @OneToMany(mappedBy = "manager")
    @ToString.Exclude
    private List<User> team; // Employees under this manager

    // Manager → HR
    @ManyToOne
    @JoinColumn(name = "hr_id")
    @ToString.Exclude
    private User hr; // HR assigned to this manager

    @OneToMany(mappedBy = "hr")
    @ToString.Exclude
    private List<User> managersUnderHR; // Managers under this HR

}
