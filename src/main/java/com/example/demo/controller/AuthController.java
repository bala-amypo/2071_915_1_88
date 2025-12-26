package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    // Constructor Injection (BEST PRACTICE)
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // ===================== REGISTER =====================
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {

        // Basic validation
        if (request.getRole() == null || request.getRole().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        User user = new User(
                null,
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                request.getRole()
        );

        User savedUser = userService.register(user);
        return ResponseEntity.ok(savedUser);
    }

    // ===================== LOGIN =====================
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        boolean isValid = userService.validateUser(
                request.getEmail(),
                request.getPassword()
        );

        if (!isValid) {
            return ResponseEntity.status(401)
                    .body(new LoginResponse("Invalid email or password"));
        }

        // Dummy token for now (JWT later)
        return ResponseEntity.ok(new LoginResponse("fake-jwt-token"));
    }
}
