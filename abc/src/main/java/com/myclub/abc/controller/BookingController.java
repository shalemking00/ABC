package com.myclub.abc.controller;

import com.myclub.abc.model.Booking;
import com.myclub.abc.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking bookingEntity) {
        try {
            return ResponseEntity.status(201).body(bookingService.createBooking(bookingEntity));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Booking>> searchBookings(
            @RequestParam(required = false) String memberName,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate) {
        return ResponseEntity.ok(bookingService.searchBookings(memberName, startDate, endDate));
    }

}
