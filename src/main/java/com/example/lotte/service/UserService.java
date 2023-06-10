package com.example.lotte.service;

import com.example.lotte.model.Employee;
import com.example.lotte.model.User;
import com.example.lotte.repository.EmployeeRepository;
import com.example.lotte.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    private EmployeeRepository emp;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
//        System.out.println(user.getEmployee());
        if (user == null) {
            throw new RuntimeException("Invalid username or password");
        }
        return user;
    }
}
