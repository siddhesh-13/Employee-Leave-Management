package com.sidd.leave.employeemanagement.config;

import com.sidd.leave.employeemanagement.entity.Role;
import com.sidd.leave.employeemanagement.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RoleSeeder implements CommandLineRunner {

    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        seedRole("SUPER_HR");
        seedRole("HR");
        seedRole("MANAGER");
        seedRole("EMPLOYEE");

    }

    private void seedRole(String roleName){
        if (!roleRepository.existsByRoleName(roleName)){
            Role role=new Role();
            role.setRoleName(roleName);
            roleRepository.save(role);
            System.out.println("Inserted role: " + roleName);
        }
    }
}
