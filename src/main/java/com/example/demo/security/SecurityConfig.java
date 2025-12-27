package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // ================= JWT PROVIDER =================
    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        return new JwtTokenProvider(
                "MySuperSecretJwtKeyForApartmentSystem123456",
                3600000L
        );
    }

    // ================= JWT FILTER =================
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(
            JwtTokenProvider jwtTokenProvider) {
        return new JwtAuthenticationFilter(jwtTokenProvider);
    }

    // ================= SECURITY FILTER CHAIN =================
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            JwtAuthenticationFilter jwtAuthenticationFilter
    ) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                // 🌍 PUBLIC
                .requestMatchers(
                        "/auth/**",
                        "/hello-servlet",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/swagger-resources/**",
                        "/webjars/**"
                ).permitAll()

                // 👤 USER MANAGEMENT — ADMIN ONLY
                .requestMatchers("/users/**")
                .hasRole("ADMIN")

                // 🏢 FACILITIES
                .requestMatchers(HttpMethod.POST, "/facilities/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/facilities/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/facilities/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/facilities/**")
                .hasAnyRole("ADMIN", "RESIDENT")

                // 📅 BOOKINGS — ADMIN & RESIDENT
                .requestMatchers("/bookings/**")
                .hasAnyRole("ADMIN", "RESIDENT")

                // 🏠 APARTMENT UNITS — ADMIN & RESIDENT
                .requestMatchers("/units/**")
                .hasAnyRole("ADMIN", "RESIDENT")

                // 🔐 EVERYTHING ELSE NEEDS LOGIN
                .anyRequest().authenticated()
            )
            .addFilterBefore(
                    jwtAuthenticationFilter,
                    UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }


    // ================= AUTHENTICATION MANAGER =================
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
