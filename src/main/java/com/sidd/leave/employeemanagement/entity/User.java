package com.sidd.leave.employeemanagement.entity;

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

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

//    leaves applied by the user
    @ToString.Exclude
    @OneToMany(mappedBy = "requestedBy", cascade = CascadeType.ALL)
    private List<LeaveRequest> leaveRequests;

//    leaves approved by the user
    @ToString.Exclude
    @OneToMany(mappedBy = "approvedBy")
    private List<LeaveRequest> approvedRequests;

}
