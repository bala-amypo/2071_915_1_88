// src/main/java/com/example/demo/repository/ApartmentUnitRepository.java
package com.example.demo.repository;

import com.example.demo.model.ApartmentUnit;
import com.example.demo.model.User;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ApartmentUnitRepository extends Repository<ApartmentUnit, Long> {
    ApartmentUnit save(ApartmentUnit unit);
    Optional<ApartmentUnit> findByOwner(User owner);
}
