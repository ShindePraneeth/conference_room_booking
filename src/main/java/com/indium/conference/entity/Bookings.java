package com.indium.conference.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id", columnDefinition = "SMALLINT UNSIGNED")
    private Integer bookingId;

    @Column(name = "location_id", columnDefinition = "SMALLINT UNSIGNED", nullable = false)
    private Integer locationId;

    @Column(name = "room_id", columnDefinition = "SMALLINT UNSIGNED", nullable = false)
    private Integer roomId;

    @Column(name = "user_id", columnDefinition = "SMALLINT UNSIGNED", nullable = false)
    private Integer userId;

    @Column(name = "booked_on", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private LocalDateTime bookedOn;

    @Column(name = "event_date", columnDefinition = "DATE", nullable = false)
    private LocalDate eventDate;

    @Column(name = "start_time", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "event_name", columnDefinition = "VARCHAR(255)", nullable = false)
    private String eventName;

    @Column(name = "status", columnDefinition = "ENUM('awaiting_approval', 'approved', 'cancelled', 'rejected')", nullable = false)
    private String status;

    @Column(name = "approved_by", columnDefinition = "SMALLINT UNSIGNED", nullable = true)
    private Integer approvedBy;

    @Column(name = "approved_on", columnDefinition = "DATETIME", nullable = true)
    private LocalDateTime approvedOn;

    @Column(name = "approval_remarks", columnDefinition = "VARCHAR(255)", nullable = true)
    private String approvalRemarks;

    @Column(name = "is_recurring", columnDefinition = "CHAR(1) DEFAULT 'n'", nullable = false)
    private char isRecurring;

    @Column(name = "recurrence_id", columnDefinition = "SMALLINT UNSIGNED", nullable = true)
    private Integer recurrenceId;

    // Getters and Setters
    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDateTime getBookedOn() {
        return bookedOn;
    }

    public void setBookedOn(LocalDateTime bookedOn) {
        this.bookedOn = bookedOn;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Integer approvedBy) {
        this.approvedBy = approvedBy;
    }

    public LocalDateTime getApprovedOn() {
        return approvedOn;
    }

    public void setApprovedOn(LocalDateTime approvedOn) {
        this.approvedOn = approvedOn;
    }

    public String getApprovalRemarks() {
        return approvalRemarks;
    }

    public void setApprovalRemarks(String approvalRemarks) {
        this.approvalRemarks = approvalRemarks;
    }

    public char getIsRecurring() {
        return isRecurring;
    }

    public void setIsRecurring(char isRecurring) {
        this.isRecurring = isRecurring;
    }

    public Integer getRecurrenceId() {
        return recurrenceId;
    }

    public void setRecurrenceId(Integer recurrenceId) {
        this.recurrenceId = recurrenceId;
    }
}
