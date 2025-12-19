// src/main/java/com/example/demo/security/JwtAuthenticationFilter.java
package com.example.demo.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class JwtAuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // No-op; not used by tests
        chain.doFilter((HttpServletRequest) request, response);
    }
}
