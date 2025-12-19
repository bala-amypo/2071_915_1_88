package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {
    boolean existsByEmail(String email);
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
}
