package com.indium.conference.controller;

import com.indium.conference.entity.*;
import com.indium.conference.service.RoomService;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<String> bookRoom(HttpServletRequest request, @RequestBody Bookings booking) {
        String role = (String) request.getAttribute("role");
        if (!role.equals("user")) {
            return ResponseEntity.status(403).body("Access Denied: Only users can book rooms.");
        }
        String result = roomService.bookRoom(booking);
        return ResponseEntity.ok(result);
    }

    // Cancel a room booking by a user
    @PostMapping("/cancel/{bookingId}")
    public ResponseEntity<String> cancelBooking(HttpServletRequest request, @PathVariable Integer bookingId) {
        String role = (String) request.getAttribute("role");
        String userId = (String) request.getAttribute("userId");

        if (!role.equals("user")) {
            return ResponseEntity.status(403).body("Access Denied: Only users can cancel bookings.");
        }

        String result = roomService.cancelBooking(bookingId, userId);
        return ResponseEntity.ok(result);
    }

    // Edit a booking request by a user
    @PutMapping("/edit/{bookingId}")
    public ResponseEntity<String> editBooking(HttpServletRequest request, @PathVariable Integer bookingId, @RequestBody Bookings updatedBooking) {
        String role = (String) request.getAttribute("role");
        String userId = (String) request.getAttribute("userId");

        if (!role.equals("user")) {
            return ResponseEntity.status(403).body("Access Denied: Only users can edit bookings.");
        }

        updatedBooking.setUserId(userId);
        String result = roomService.editBooking(bookingId, updatedBooking);
        return ResponseEntity.ok(result);
    }

    // Admin: Approve or reject booking
    @PostMapping("/admin/approve/{bookingId}")
    public ResponseEntity<String> approveBooking(HttpServletRequest request, @PathVariable Integer bookingId, @RequestParam Boolean approve, @RequestParam(required = false) String remarks) {
        String role = (String) request.getAttribute("role");
        String adminId = (String) request.getAttribute("userId");

        if (!role.equals("admin") && !role.equals("superadmin")) {
            return ResponseEntity.status(403).body("Access Denied: Only admins can approve bookings.");
        }

        String result = roomService.approveOrRejectBooking(bookingId, adminId, approve, remarks);
        return ResponseEntity.ok(result);
    }

    // Admin: Add a new room
    @PostMapping("/admin/addRoom")
    public ResponseEntity<String> addRoom(HttpServletRequest request, @RequestBody RoomDetails roomDetails) {
        String role = (String) request.getAttribute("role");
        String userId = (String) request.getAttribute("userId");

        if (!role.equals("admin") && !role.equals("superadmin")) {
            return ResponseEntity.status(403).body("Access Denied: Only admins can add rooms.");
        }

        roomDetails.setCreatedBy(userId);
        String result = roomService.addNewRoom(roomDetails);
        return ResponseEntity.ok(result);
    }

    // Add a new location (Super Admin only)
    @PostMapping("/admin/addLocation")
    public ResponseEntity<String> addLocation(HttpServletRequest request, @RequestBody LocationDetails locationDetails) {
        String role = (String) request.getAttribute("role");

        if (!role.equals("superadmin")) {
            return ResponseEntity.status(403).body("Access Denied: Only super admins can add locations.");
        }

        String result = roomService.addNewLocation(locationDetails);
        return ResponseEntity.ok(result);
    }

    // Admin: Block a room for a time period
    @PostMapping("/admin/block")
    public ResponseEntity<String> blockRoom(HttpServletRequest request, @RequestParam Integer roomId, @RequestParam String startTime, @RequestParam String endTime) {
        String role = (String) request.getAttribute("role");
        if (!role.equals("admin") && !role.equals("superadmin")) {
            return ResponseEntity.status(403).body("Access Denied: Only admins can block rooms.");
        }

        String result = roomService.blockRoom(roomId, startTime, endTime);
        return ResponseEntity.ok(result);
    }
}
