package com.sidd.leave.employeemanagement.security;

import com.sidd.leave.employeemanagement.entity.User;
import com.sidd.leave.employeemanagement.exception.UserNotFoundException;
import com.sidd.leave.employeemanagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws RuntimeException {

        User user = userRepository.findByEmail(email).orElseThrow(()->
                 new UserNotFoundException("User doesn't exists")
        );

        return new CustomUserDetails(user);
    }
}
