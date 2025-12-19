package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ApartmentUnit;
import com.example.demo.repository.ApartmentUnitRepository;
import com.example.demo.service.ApartmentUnitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentUnitServiceImpl implements ApartmentUnitService {

    private final ApartmentUnitRepository apartmentUnitRepository;

    public ApartmentUnitServiceImpl(ApartmentUnitRepository apartmentUnitRepository) {
        this.apartmentUnitRepository = apartmentUnitRepository;
    }

    @Override
    public List<ApartmentUnit> findAll() {
        return apartmentUnitRepository.findAll();
    }

    @Override
    public ApartmentUnit findById(Long id) {
        return apartmentUnitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ApartmentUnit not found with id: " + id));
    }

    @Override
    public ApartmentUnit save(ApartmentUnit unit) {
        return apartmentUnitRepository.save(unit);
    }

    @Override
    public void deleteById(Long id) {
        if (!apartmentUnitRepository.existsById(id)) {
            throw new ResourceNotFoundException("ApartmentUnit not found with id: " + id);
        }
        apartmentUnitRepository.deleteById(id);
    }
}
