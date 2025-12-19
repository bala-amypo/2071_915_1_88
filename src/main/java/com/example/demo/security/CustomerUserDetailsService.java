// src/main/java/com/example/demo/security/CustomUserDetailsService.java
package com.example.demo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) {
        // Not used in tests; stubbed by mocks
        throw new UnsupportedOperationException("Not implemented");
    }
}
