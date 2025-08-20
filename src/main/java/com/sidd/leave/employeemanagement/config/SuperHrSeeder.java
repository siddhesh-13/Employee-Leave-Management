package com.sidd.leave.employeemanagement.config;

import com.sidd.leave.employeemanagement.entity.Role;
import com.sidd.leave.employeemanagement.entity.User;
import com.sidd.leave.employeemanagement.enums.UserStatus;
import com.sidd.leave.employeemanagement.repository.RoleRepository;
import com.sidd.leave.employeemanagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SuperHrSeeder implements CommandLineRunner {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Value("${app.superhr.default-password:SuperHr12}")
    private String hrPassword;

    public SuperHrSeeder(UserRepository userRepository,
                         RoleRepository roleRepository,
                         PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        String email="superhr@company.com";

        if (!userRepository.existsByEmail(email)){

            Role superHrRole= roleRepository.findByRoleName("SUPER_HR").orElseThrow(()->
                    new RuntimeException("Super HR role doesn't exist"));


            User superHr=new User();
            superHr.setFullName("Super HR");
            superHr.setUsername("superhr");
            superHr.setEmail(email);
            superHr.setPassword(passwordEncoder.encode(hrPassword));
            superHr.setUserStatus(UserStatus.ACTIVE);
            superHr.setRole(superHrRole);

            userRepository.save(superHr);

            System.out.println("Super HR user created: " + email + " " + superHr.getFullName());
        }

    }
}
