package com.example.demo.controller;

import com.example.demo.model.ApartmentUnit;
import com.example.demo.service.ApartmentUnitService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/units")
public class ApartmentUnitController {

    private final ApartmentUnitService apartmentUnitService;

    public ApartmentUnitController(ApartmentUnitService apartmentUnitService) {
        this.apartmentUnitService = apartmentUnitService;
    }

    @PostMapping("/assign/{userId}")
    public ApartmentUnit assignUnit(@PathVariable Long userId, @RequestBody ApartmentUnit unit) {
        return apartmentUnitService.assignUnitToUser(userId, unit);
    }

    @GetMapping("/user/{userId}")
    public ApartmentUnit getUnitByUser(@PathVariable Long userId) {
        return apartmentUnitService.getUnitByUser(userId);
    }
}
