package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        if (user.getRole() == null || user.getRole().isBlank()) {
            throw new BadRequestException("Role must be specified");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateRole(Long userId, String role) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public boolean validateUser(String email, String password) {

        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            return false;
        }

        User user = userOpt.get();
        return passwordEncoder.matches(password, user.getPassword());
    }
}
