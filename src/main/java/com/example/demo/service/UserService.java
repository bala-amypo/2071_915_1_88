package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    User register(User user);
    User updateRole(Long userId, String role);
    User getUserById(Long id);
}
