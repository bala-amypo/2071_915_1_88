package com.example.demo.service;

import com.example.demo.model.User;
import java.util.List;

public interface UserService {
    User register(User user);
    User findByEmail(String email);
    User login(String email, String password);
    User getUserById(Long id);
    List<User> getAllUsers();
}  