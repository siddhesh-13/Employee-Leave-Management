package com.sidd.leave.employeemanagement.service;

import com.sidd.leave.employeemanagement.dto.UserRequestDto;
import com.sidd.leave.employeemanagement.dto.UserResponseDto;
import com.sidd.leave.employeemanagement.entity.User;
import com.sidd.leave.employeemanagement.repository.RoleRepository;
import com.sidd.leave.employeemanagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        User user= modelMapper.map(userRequestDto, User.class);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return modelMapper.map(user, UserResponseDto.class);
    }
}
