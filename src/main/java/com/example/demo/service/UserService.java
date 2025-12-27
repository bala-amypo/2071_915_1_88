package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {

    User register(User user);

    User updateRole(Long userId, String role);

    User getUserById(Long id);

    boolean validateUser(String email, String password);

    User findByEmail(String email);
}
