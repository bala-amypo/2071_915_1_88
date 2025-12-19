// src/main/java/com/example/demo/repository/BookingRepository.java
package com.example.demo.repository;

import com.example.demo.model.Booking;
import com.example.demo.model.Facility;
import org.springframework.data.repository.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends Repository<Booking, Long> {
    Optional<Booking> findById(Long id);
    Booking save(Booking booking);
    List<Booking> findByFacilityAndStartTimeLessThanAndEndTimeGreaterThan(
            Facility facility, LocalDateTime startLessThan, LocalDateTime endGreaterThan);
}
