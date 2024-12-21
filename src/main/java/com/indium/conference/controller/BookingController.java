package com.indium.conference.controller;

import com.indium.conference.entity.AuditLog;
import com.indium.conference.entity.Bookings;
import com.indium.conference.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<String> bookRoom(@RequestBody Bookings booking) {
        bookingService.bookRoom(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body("Booking created successfully.");
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<String> editBooking(@PathVariable Integer bookingId, @RequestBody Bookings bookingRequest) {
        bookingService.editBooking(bookingId, bookingRequest);
        return ResponseEntity.ok("Booking updated successfully.");
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable Integer bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok("Booking cancelled successfully.");
    }

    @PostMapping("/bookings/{bookingId}/approve")
    public ResponseEntity<String> approveBooking(
            @PathVariable Integer bookingId,
            @RequestParam String adminUserId,  // Admin user ID passed as a request parameter
            @RequestBody Bookings booking) {
        bookingService.approveBooking(bookingId, adminUserId, booking);
        return ResponseEntity.ok("Booking approved successfully.");
    }

    @GetMapping("/{bookingId}/history")
    public ResponseEntity<List<AuditLog>> getBookingHistory(@PathVariable Integer bookingId) {
        return ResponseEntity.ok(bookingService.getBookingHistory(bookingId));
    }
}
