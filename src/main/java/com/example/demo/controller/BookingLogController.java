package com.example.demo.controller;

import com.example.demo.model.BookingLog;
import com.example.demo.service.BookingLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings/{bookingId}/logs")
public class BookingLogController {

    private final BookingLogService bookingLogService;

    public BookingLogController(BookingLogService bookingLogService) {
        this.bookingLogService = bookingLogService;
    }

    @PostMapping
    public BookingLog addLog(@PathVariable Long bookingId, @RequestParam String message) {
        return bookingLogService.addLog(bookingId, message);
    }

    @GetMapping
    public List<BookingLog> getLogs(@PathVariable Long bookingId) {
        return bookingLogService.getLogsByBooking(bookingId);
    }
}
