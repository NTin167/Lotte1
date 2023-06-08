package com.example.lotte.service;

import com.example.lotte.model.User;
import com.example.lotte.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user == null) {
            throw new RuntimeException("Invalid username or password");
        }
        return user;
    }
}
