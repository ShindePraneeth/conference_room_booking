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

    @Autowired
    private LocationDetailsRepository locationDetailsRepository;

    // Fetch available rooms
    public List<RoomDetails> getAvailableRooms() {

        return roomDetailsRepository.findAvailableRooms();
    }

    public String addLocation(LocationDetails locationDetails) {
        locationDetails.setCreatedOn(LocalDateTime.now()); // Set the created timestamp
        locationDetailsRepository.save(locationDetails);
        return "Booking request submitted successfully.";
    }
    // Book a room for a user
    public String bookRoom(Bookings booking) {
        booking.setStatus("awaiting_approval");
        bookingsRepository.save(booking);
        return "Booking request submitted successfully.";
    }

    // Cancel a room booking
    public String cancelBooking(Integer bookingId, String userId) {
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

    // Edit a booking request by a user
    public String editBooking(Integer bookingId, Bookings updatedBooking) {
        Optional<Bookings> existingBooking = bookingsRepository.findById(bookingId);
        if (existingBooking.isPresent() && existingBooking.get().getUserId().equals(updatedBooking.getUserId())) {
            Bookings bookingToUpdate = existingBooking.get();
            bookingToUpdate.setEventName(updatedBooking.getEventName());
            bookingToUpdate.setStartTime(updatedBooking.getStartTime());
            bookingToUpdate.setEndTime(updatedBooking.getEndTime());
            bookingToUpdate.setEventDate(updatedBooking.getEventDate());
            bookingsRepository.save(bookingToUpdate);
            return "Booking updated successfully.";
        }
        return "Booking not found or user mismatch.";
    }

    // Approve or reject a booking
    public String approveOrRejectBooking(Integer bookingId, String adminId, Boolean approve, String remarks) {
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
        roomDetails.setCreatedOn(LocalDateTime.now());
        roomDetailsRepository.save(roomDetails);
        return "New room added successfully.";
    }

    // Add a new location (Super Admin only)
    public String addNewLocation(LocationDetails locationDetails) {
        locationDetails.setIsActive('y');
        locationDetailsRepository.save(locationDetails);
        return "New location added successfully.";
    }

    // Block a room for a specific time
    public String blockRoom(Integer roomId, String startTime, String endTime) {
        Optional<RoomDetails> room = roomDetailsRepository.findById(roomId);
        if (room.isPresent()) {
            // Implement logic to block room (e.g., update availability in database)
            return "Room blocked from " + startTime + " to " + endTime;
        }
        return "Room not found.";
    }
}
