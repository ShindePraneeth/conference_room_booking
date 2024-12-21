package com.indium.conference.service;

import com.indium.conference.entity.AuditLog;
import com.indium.conference.entity.Bookings;
import com.indium.conference.entity.UserDetails;
import com.indium.conference.exception.ResourceNotFoundException;
import com.indium.conference.repository.AuditLogRepository;
import com.indium.conference.repository.BookingsRepository;
import com.indium.conference.repository.UserDetailsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class BookingService {

    @Autowired
    private BookingsRepository bookingRepository;

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    /**
     * Book a room.
     */
    public void bookRoom(Bookings bookingRequest) {
        // Create booking
        Bookings booking = new Bookings();
        booking.setLocationId(bookingRequest.getLocationId());
        booking.setRoomId(bookingRequest.getRoomId());
        booking.setUserId(bookingRequest.getUserId());
        booking.setBookedOn(LocalDateTime.now());
        booking.setEventDate(bookingRequest.getEventDate());
        booking.setStartTime(bookingRequest.getStartTime());
        booking.setEndTime(bookingRequest.getEndTime());
        booking.setEventName(bookingRequest.getEventName());
        booking.setStatus("awaiting_approval");
        booking.setIsRecurring(bookingRequest.getIsRecurring());
        booking.setRecurrenceId(bookingRequest.getRecurrenceId());

        bookingRepository.save(booking);

        // Record in audit log
        AuditLog auditLog = new AuditLog();
        auditLog.setBookingId(booking.getBookingId());
        auditLog.setChangeType("modified");  // Change type as 'created' when a new booking is made
        auditLog.setComments("Booking created.");
        auditLog.setTimestamp(LocalDateTime.now());
        auditLogRepository.save(auditLog);
    }

    /**
     * Edit booking details.
     * This method updates an existing booking and logs the change in the audit log.
     */
    public void editBooking(Integer bookingId, Bookings bookingRequest) {
        // Step 1: Find the existing booking
        Bookings booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        // Step 2: Check if the booking is approved, and if so, throw an exception as it cannot be edited
        if ("approved".equalsIgnoreCase(booking.getStatus())) {
            throw new IllegalStateException("Cannot edit an approved booking.");
        }

        // Step 3: Store the old values for the booking (before update)
        String oldEventName = booking.getEventName();
        LocalDateTime oldStartTime = booking.getStartTime();
        LocalDateTime oldEndTime = booking.getEndTime();

        // Step 4: Update the booking with new values from bookingRequest
        booking.setEventDate(bookingRequest.getEventDate());
        booking.setStartTime(bookingRequest.getStartTime());
        booking.setEndTime(bookingRequest.getEndTime());
        booking.setEventName(bookingRequest.getEventName());

        // Step 5: Save the updated booking (we are updating the same booking, not creating a new one)
        bookingRepository.save(booking);

        // Step 6: Record the change in the audit log
        AuditLog auditLog = new AuditLog();
        auditLog.setBookingId(booking.getBookingId());
        auditLog.setChangeType("modified");
        auditLog.setComments("Booking modified. Old values - eventName: " + oldEventName + ", startTime: " + oldStartTime + ", endTime: " + oldEndTime);
        auditLog.setTimestamp(LocalDateTime.now());
        auditLogRepository.save(auditLog);
    }

    /**
     * Cancel a booking.
     * This method updates the booking status to "cancelled" and logs the change in the audit log.
     */
    public void cancelBooking(Integer bookingId) {
        Bookings booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        booking.setStatus("cancelled");
        bookingRepository.save(booking);

        // Record the cancellation in the audit log
        AuditLog auditLog = new AuditLog();
        auditLog.setBookingId(booking.getBookingId());
        auditLog.setChangeType("cancelled");
        auditLog.setComments("Booking cancelled.");
        auditLog.setTimestamp(LocalDateTime.now());
        auditLogRepository.save(auditLog);
    }

    /**
     * Approve a booking.
     * This method approves the booking and records the approval in the booking and the audit log.
     */
    public void approveBooking(Integer bookingId, String userId, Bookings updatedBooking) {
        // Fetch the user details of the approver
        UserDetails approver = userDetailsRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Check if the user has the admin role
        if (!"admin".equalsIgnoreCase(approver.getRoleType())) {
            throw new IllegalStateException("Only admin users can approve bookings.");
        }

        // Fetch the booking from the database
        Bookings existingBooking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        // Update the relevant fields for approval
        existingBooking.setStatus("approved");
        existingBooking.setApprovedBy(userId);
        existingBooking.setApprovedOn(LocalDateTime.now());
        existingBooking.setApprovalRemarks(updatedBooking.getApprovalRemarks());

        // Save the updated booking
        bookingRepository.save(existingBooking);

        // Record the approval in the audit log
        AuditLog auditLog = new AuditLog();
        auditLog.setBookingId(existingBooking.getBookingId());
        auditLog.setChangeType("approved");
        auditLog.setComments("Booking approved by admin user " + userId);
        auditLog.setTimestamp(LocalDateTime.now());
        auditLogRepository.save(auditLog);
    }

    /**
     * Get booking history.
     * This method retrieves all audit log entries for a particular booking.
     */
    public List<AuditLog> getBookingHistory(Integer bookingId) {
        return auditLogRepository.findByBookingId(bookingId);
    }
}
