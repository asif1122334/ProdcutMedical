package com.example.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.LoginRequest;
import com.example.model.SessionDetails;
import com.example.service.LoginService;

@RestController
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public SessionDetails login(@RequestBody LoginRequest loginRequest) throws UnsupportedEncodingException {
        // You can add additional validation or business logic if needed before calling the service
        return loginService.login(loginRequest.getUsername(), loginRequest.getPassword());
    }
}

