package com.example.demo.repository;

import com.example.demo.model.Facility;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface FacilityRepository extends Repository<Facility, Long> {
    Optional<Facility> findById(Long id);
    Optional<Facility> findByName(String name);
    Facility save(Facility facility);
    List<Facility> findAll();
}
