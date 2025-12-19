// src/main/java/com/example/demo/service/impl/BookingServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.exception.ConflictException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Booking;
import com.example.demo.model.Facility;
import com.example.demo.model.User;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.FacilityRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BookingLogService;
import com.example.demo.service.BookingService;

import java.util.List;
import org.springframework.stereotype.Service;

@Service   // ✅ Add this annotation
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final FacilityRepository facilityRepository;
    private final UserRepository userRepository;
    private final BookingLogService bookingLogService;

    public BookingServiceImpl(BookingRepository bookingRepository,
                              FacilityRepository facilityRepository,
                              UserRepository userRepository,
                              BookingLogService bookingLogService) {
        this.bookingRepository = bookingRepository;
        this.facilityRepository = facilityRepository;
        this.userRepository = userRepository;
        this.bookingLogService = bookingLogService;
    }

    @Override
    public Booking createBooking(Long facilityId, Long userId, Booking booking) {
        Facility facility = facilityRepository.findById(facilityId)
                .orElseThrow(() -> new ResourceNotFoundException("Facility not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Booking> conflicts = bookingRepository
                .findByFacilityAndStartTimeLessThanAndEndTimeGreaterThan(
                        facility, booking.getEndTime(), booking.getStartTime());
        if (!conflicts.isEmpty()) {
            throw new ConflictException("Booking conflict");
        }

        booking.setFacility(facility);
        booking.setUser(user);
        if (booking.getStatus() == null) booking.setStatus(Booking.STATUS_CONFIRMED);

        Booking saved = bookingRepository.save(booking);

        // ✅ Use the saved booking directly for logging
        bookingLogService.addLog(saved.getId(), "Created");

        return saved;
    }

    @Override
    public Booking cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        booking.setStatus(Booking.STATUS_CANCELLED);
        Booking saved = bookingRepository.save(booking);
        bookingLogService.addLog(saved.getId(), "Cancelled");
        return saved;
    }

    @Override
    public Booking getBooking(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
    }
}
