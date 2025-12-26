package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String secretKey;
    private final long expiration = 86400000; // 1 day

    // ✅ REQUIRED BY TEST
    public JwtTokenProvider() {
        this.secretKey = "secret-key";
    }

    // ✅ REQUIRED BY TEST
    public JwtTokenProvider(String secretKey) {
        this.secretKey = secretKey;
    }

    // ✅ REQUIRED BY TEST (4 arguments)
    public String generateToken(Authentication authentication,
                                Long userId,
                                String email,
                                String role) {

        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // ================= TOKEN VALIDATION =================
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ================= EXTRACTION METHODS =================
    // ✅ REQUIRED BY TEST
    public String getEmailFromToken(String token) {
        return getClaims(token).getSubject();
    }

    // ✅ REQUIRED BY TEST
    public String getRoleFromToken(String token) {
        return getClaims(token).get("role", String.class);
    }

    public Long getUserIdFromToken(String token) {
        return getClaims(token).get("userId", Long.class);
    }

    // ================= INTERNAL =================
    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
