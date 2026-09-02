package com.example.service;

import com.example.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {

    private final List<User> users = new ArrayList<>();

    public List<User> findAll() {
        return List.copyOf(users);
    }

    public Optional<User> findById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public User save(User user) {
        users.add(user);
        return user;
    }
}