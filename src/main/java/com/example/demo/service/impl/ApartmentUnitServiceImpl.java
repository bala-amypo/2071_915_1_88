// src/main/java/com/example/demo/service/impl/ApartmentUnitServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.exception.RequestNotFoundException;
import com.example.demo.model.ApartmentUnit;
import com.example.demo.model.User;
import com.example.demo.repository.ApartmentUnitRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ApartmentUnitService;

public class ApartmentUnitServiceImpl implements ApartmentUnitService {
    private final ApartmentUnitRepository apartmentUnitRepository;
    private final UserRepository userRepository;

    public ApartmentUnitServiceImpl(ApartmentUnitRepository apartmentUnitRepository,
                                    UserRepository userRepository) {
        this.apartmentUnitRepository = apartmentUnitRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ApartmentUnit assignUnitToUser(Long userId, ApartmentUnit unit) {
        User owner = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        unit.setOwner(owner);
        ApartmentUnit saved = apartmentUnitRepository.save(unit);
        owner.setApartmentUnit(saved);
        return saved;
    }

    @Override
    public ApartmentUnit getUnitByUser(Long userId) {
        User owner = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return apartmentUnitRepository.findByOwner(owner)
                .orElseThrow(() -> new ResourceNotFoundException("Unit not found"));
    }
}
