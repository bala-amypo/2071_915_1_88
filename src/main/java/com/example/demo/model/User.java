package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String role = "RESIDENT";

    @OneToOne(mappedBy = "owner")
    private ApartmentUnit apartmentUnit;

    public User() {}

    public User(Long id, String name, String email, String password, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() { 
          return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public void setApartmentUnit(ApartmentUnit apartmentUnit) {
        this.apartmentUnit = apartmentUnit;
    }
} 
