package com.example.Day13Homework.controller;


import com.example.Day13Homework.dto.UserDTO;
import com.example.Day13Homework.request.LoginRequest;
import com.example.Day13Homework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;


    @PostMapping("/login")
    public UserDTO login(@RequestBody LoginRequest request){

        return service.checkLogin(request);
    }


}
