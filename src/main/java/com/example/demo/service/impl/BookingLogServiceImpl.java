package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.BookingLog;
import com.example.demo.repository.BookingLogRepository;
import com.example.demo.service.BookingLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingLogServiceImpl implements BookingLogService {

    private final BookingLogRepository bookingLogRepository;

    public BookingLogServiceImpl(BookingLogRepository bookingLogRepository) {
        this.bookingLogRepository = bookingLogRepository;
    }

    @Override
    public List<BookingLog> findAll() {
        return bookingLogRepository.findAll();
    }

    @Override
    public BookingLog findById(Long id) {
        return bookingLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BookingLog not found with id: " + id));
    }

    @Override
    public BookingLog save(BookingLog log) {
        return bookingLogRepository.save(log);
    }

    @Override
    public void deleteById(Long id) {
        if (!bookingLogRepository.existsById(id)) {
            throw new ResourceNotFoundException("BookingLog not found with id: " + id);
        }
        bookingLogRepository.deleteById(id);
    }

    @Override
    public List<BookingLog> getLogsByBooking(Long bookingId) {
        return bookingLogRepository.findByBookingId(bookingId);
    }
}
