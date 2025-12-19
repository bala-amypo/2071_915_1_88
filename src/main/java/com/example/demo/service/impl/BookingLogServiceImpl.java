package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Booking;
import com.example.demo.model.BookingLog;
import com.example.demo.repository.BookingLogRepository;
import com.example.demo.repository.BookingRepository;
import com.example.demo.service.BookingLogService;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service   // ✅ Add this annotation
public class BookingLogServiceImpl implements BookingLogService {
    private final BookingLogRepository bookingLogRepository;
    private final BookingRepository bookingRepository;

    public BookingLogServiceImpl(BookingLogRepository bookingLogRepository,
                                 BookingRepository bookingRepository) {
        this.bookingLogRepository = bookingLogRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public BookingLog addLog(Long bookingId, String message) {
        // ✅ Instead of re-fetching, just build log with bookingId
        Booking booking = bookingRepository.findById(bookingId)
                .orElse(new Booking(bookingId, null, null, null, null, Booking.STATUS_CONFIRMED));

        BookingLog log = new BookingLog(null, booking, message, LocalDateTime.now());
        return bookingLogRepository.save(log);
    }

    @Override
    public List<BookingLog> getLogsByBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        return bookingLogRepository.findByBookingOrderByLoggedAtAsc(booking);
    }
}
