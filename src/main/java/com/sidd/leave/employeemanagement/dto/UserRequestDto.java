package com.sidd.leave.employeemanagement.dto;

import com.sidd.leave.employeemanagement.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    private String fullName;
    private String username;
    private String email;
    private String password;
    private Gender gender;
    private LocalDate birthDate;
}
