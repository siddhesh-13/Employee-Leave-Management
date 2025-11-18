package com.sidd.leave.employeemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(){
        return "login";
    }

    @GetMapping("/register")
    public String registrationPage(){
        return "register";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
}
