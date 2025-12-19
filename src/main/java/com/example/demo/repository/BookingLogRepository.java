// src/main/java/com/example/demo/repository/BookingLogRepository.java
package com.example.demo.repository;

import com.example.demo.model.Booking;
import com.example.demo.model.BookingLog;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface BookingLogRepository extends Repository<BookingLog, Long> {
    BookingLog save(BookingLog log);
    List<BookingLog> findByBookingOrderByLoggedAtAsc(Booking booking);
}
