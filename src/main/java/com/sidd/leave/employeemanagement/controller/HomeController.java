package com.sidd.leave.employeemanagement.controller;

import com.sidd.leave.employeemanagement.dto.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class HomeController {

//    @Autowired
//    private AuthRequest authRequest;

//    @GetMapping("/")
//    public String homePage(){
//        return "login";
//    }
//
//    @GetMapping("/register")
//    public String registrationPage(){
//        return "register";
//    }
//
//    @GetMapping("/login")
//    public String loginPage(){
//        return "login";
//    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello(@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok("Hello");
    }
}
