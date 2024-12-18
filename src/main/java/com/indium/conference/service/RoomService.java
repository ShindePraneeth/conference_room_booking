
package com.indium.conference.service;

import com.indium.conference.entity.*;
import com.indium.conference.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomDetailsRepository roomDetailsRepository;

    @Autowired
    private BookingsRepository bookingsRepository;

    @Autowired
    private AuditLogRepository auditLogRepository;

    // Fetch available rooms
    public List<RoomDetails> getAvailableRooms() {
        return roomDetailsRepository.findByIsActiveAndBookingAllowed('y', 'y');
    }

    // Book a room for a user
    public String bookRoom(Bookings booking) {
        booking.setStatus("awaiting_approval");
        bookingsRepository.save(booking);
        return "Booking request submitted successfully.";
    }

    // Cancel a room booking
    public String cancelBooking(Integer bookingId, Integer userId) {
        Optional<Bookings> booking = bookingsRepository.findById(bookingId);
        if (booking.isPresent() && booking.get().getUserId().equals(userId)) {
            Bookings updatedBooking = booking.get();
            updatedBooking.setStatus("cancelled");
            bookingsRepository.save(updatedBooking);
            // Log the cancellation
            AuditLog log = new AuditLog();
            log.setBookingId(bookingId);
            log.setCreatedBy(userId);
            log.setChangeType("cancelled");
            log.setTimestamp(LocalDateTime.now());
            log.setComments("User canceled the booking.");
            auditLogRepository.save(log);
            return "Booking cancelled successfully.";
        }
        return "Booking not found or user mismatch.";
    }

    // Approve or reject a booking
    public String approveOrRejectBooking(Integer bookingId, Integer adminId, Boolean approve, String remarks) {
        Optional<Bookings> booking = bookingsRepository.findById(bookingId);
        if (booking.isPresent()) {
            Bookings updatedBooking = booking.get();
            updatedBooking.setStatus(approve ? "approved" : "rejected");
            updatedBooking.setApprovedBy(adminId);
            updatedBooking.setApprovedOn(LocalDateTime.now());
            updatedBooking.setApprovalRemarks(remarks);
            bookingsRepository.save(updatedBooking);
            return "Booking " + (approve ? "approved." : "rejected.");
        }
        return "Booking not found.";
    }

    // Add a new room
    public String addNewRoom(RoomDetails roomDetails) {
        roomDetails.setIsActive('y');
        roomDetailsRepository.save(roomDetails);
        return "New room added successfully.";
    }

    // Block a room for a specific time
    public String blockRoom(Integer roomId, String startTime, String endTime) {
        Optional<RoomDetails> room = roomDetailsRepository.findById(roomId);
        if (room.isPresent()) {
            // Implement logic to block room
            return "Room blocked from " + startTime + " to " + endTime;
        }
        return "Room not found.";
    }
}