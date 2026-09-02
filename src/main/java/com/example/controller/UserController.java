package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public List<User> getAllUsers() {
        return userService.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userService.findById(id);
    }

    public User createUser(User user) {
        return userService.save(user);
    }
}