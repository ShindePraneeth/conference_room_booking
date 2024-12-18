package com.indium.conference.controller;

import com.indium.conference.entity.*;
import com.indium.conference.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // View available rooms
    @GetMapping("/available")
    public ResponseEntity<List<RoomDetails>> viewAvailableRooms() {
        List<RoomDetails> availableRooms = roomService.getAvailableRooms();
        return ResponseEntity.ok(availableRooms);
    }

    // Book a room for a user
    @PostMapping("/book")
    public ResponseEntity<String> bookRoom(@RequestBody Bookings booking) {
        String result = roomService.bookRoom(booking);
        return ResponseEntity.ok(result);
    }

    // Cancel a room booking by a user
    @PostMapping("/cancel/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable Integer bookingId, @RequestParam Integer userId) {
        String result = roomService.cancelBooking(bookingId, userId);
        return ResponseEntity.ok(result);
    }

    // Edit a booking request by a user
    @PutMapping("/edit/{bookingId}")
    public ResponseEntity<String> editBooking(@PathVariable Integer bookingId, @RequestBody Bookings updatedBooking) {
        String result = roomService.editBooking(bookingId, updatedBooking);
        return ResponseEntity.ok(result);
    }

    // Admin: Approve or reject booking
    @PostMapping("/admin/approve/{bookingId}")
    public ResponseEntity<String> approveBooking(@PathVariable Integer bookingId, @RequestParam Integer adminId, @RequestParam Boolean approve, @RequestParam(required = false) String remarks) {
        String result = roomService.approveOrRejectBooking(bookingId, adminId, approve, remarks);
        return ResponseEntity.ok(result);
    }

    // Admin: Add a new room
    @PostMapping("/admin/add")
    public ResponseEntity<String> addRoom(@RequestBody RoomDetails roomDetails) {
        String result = roomService.addNewRoom(roomDetails);
        return ResponseEntity.ok(result);
    }

    // Admin: Block a room for a time period
    @PostMapping("/admin/block")
    public ResponseEntity<String> blockRoom(@RequestParam Integer roomId, @RequestParam String startTime, @RequestParam String endTime) {
        String result = roomService.blockRoom(roomId, startTime, endTime);
        return ResponseEntity.ok(result);
    }
}