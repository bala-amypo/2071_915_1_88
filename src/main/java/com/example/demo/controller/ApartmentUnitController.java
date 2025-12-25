package com.example.demo.controller;

import com.example.demo.model.ApartmentUnit;
import com.example.demo.service.ApartmentUnitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/units")
public class ApartmentUnitController {

    private final ApartmentUnitService apartmentUnitService;

    public ApartmentUnitController(ApartmentUnitService apartmentUnitService) {
        this.apartmentUnitService = apartmentUnitService;
    }

    // ✅ Assign a unit to a user
    @PostMapping("/assign/{userId}")
    public ResponseEntity<ApartmentUnit> assignUnit(@PathVariable Long userId,
                                                    @RequestBody ApartmentUnit unit) {
        ApartmentUnit savedUnit = apartmentUnitService.assignUnitToUser(userId, unit);
        return ResponseEntity.ok(savedUnit);
    }

    // ✅ Get unit by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApartmentUnit> getUnitByUser(@PathVariable Long userId) {
        ApartmentUnit unit = apartmentUnitService.getUnitByUser(userId);
        if (unit == null) {
            return ResponseEntity.notFound().build(); // ✅ return 404 if no unit found
        }
        return ResponseEntity.ok(unit);
    }
}
