package com.example.lotte.controller;

import com.example.lotte.model.User;
import com.example.lotte.request.LoginRequest;
import com.example.lotte.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("ĐÃ GỌI API");
        try {
            System.out.println(loginRequest.getUsername());
            User user = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
            return ResponseEntity.ok(user.getRole());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}