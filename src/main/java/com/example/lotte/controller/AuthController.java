package com.example.lotte.controller;

import com.example.lotte.DTO.UserDTO;
import com.example.lotte.exception.ResourceNotFoundException;
import com.example.lotte.model.Staff;
import com.example.lotte.model.User;
import com.example.lotte.repository.StaffRepository;
import com.example.lotte.request.LoginRequest;
import com.example.lotte.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private StaffRepository staffRepository;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            System.out.println(loginRequest.getUsername());
            User user = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
            Staff staff = staffRepository.findByAccount(
                    user.getId()).orElseThrow(()-> new ResourceNotFoundException("Staff", "id", user.getId()));
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setPassword(user.getPassword());
            userDTO.setUsername(user.getUsername());
            userDTO.setRole(user.getRole());
            userDTO.setStatus(user.getStatus());
            userDTO.setEmployeeId(staff.getId());


            return ResponseEntity.ok(userDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}