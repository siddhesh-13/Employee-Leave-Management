package com.sidd.leave.employeemanagement.service;

import com.sidd.leave.employeemanagement.dto.UserRequestDto;
import com.sidd.leave.employeemanagement.dto.UserResponseDto;
import com.sidd.leave.employeemanagement.entity.Role;
import com.sidd.leave.employeemanagement.entity.User;
import com.sidd.leave.employeemanagement.enums.UserStatus;
import com.sidd.leave.employeemanagement.repository.RoleRepository;
import com.sidd.leave.employeemanagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<UserResponseDto> getAllPendingUsers() {
        List<User> users = userRepository.findByUserStatus(UserStatus.PENDING);

        return users.stream()
                .map(user -> modelMapper.map(user, UserResponseDto.class))
                .collect(Collectors.toList());

    }

    public User getApprovingHr(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email=auth.getName();
        return userRepository.findByEmail(email).orElseThrow(() ->{
            return new UsernameNotFoundException("Approver not found");
        });
    }

    @Override
    public UserResponseDto updateUser(Long id, UserStatus userStatus, String roleName) {
        User user = userRepository.findById(id).orElseThrow(()->{
            return new UsernameNotFoundException("User not found");
        });

        user.setUserStatus(userStatus);

        if (user.getUserStatus().equals(UserStatus.ACTIVE)){
            Role role= roleRepository.findByRoleName(roleName).orElseThrow(()->{
                return new RuntimeException("Role not found");
            });
            user.setRole(role);
            User newUser=getApprovingHr();
            user.setHr(newUser);
        }

        userRepository.save(user);

        return modelMapper.map(user,UserResponseDto.class);

    }

    @Override
    public void assignManager(Long userId, Long managerId) {

        User user= userRepository.findById(userId).orElseThrow(()->{
            return new UsernameNotFoundException("User not found");
        });

        Role role= roleRepository.findById(4).orElseThrow();

        if (user.getUserStatus().equals(UserStatus.ACTIVE) && user.getManager()==null && user.getRole().equals(role)){
            User manager= userRepository.findById(managerId).orElseThrow(()->{
                return new UsernameNotFoundException("user not found");
            });

            user.setManager(manager);
            userRepository.save(user);

        }

    }


}
