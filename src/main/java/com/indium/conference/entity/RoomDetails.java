package com.indium.conference.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "room_details")
public class RoomDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id", columnDefinition = "SMALLINT UNSIGNED")
    private Integer roomId;

    @Column(name = "room_name", columnDefinition = "VARCHAR(255)", nullable = false)
    private String roomName;

    @Column(name = "external_display_availability", columnDefinition = "CHAR(1)", nullable = false)
    private char externalDisplayAvailability;

    @Column(name = "facilities", columnDefinition = "LONGTEXT", nullable = false)
    private String facilities;

    @Column(name = "capacity", columnDefinition = "SMALLINT", nullable = false)
    private Integer capacity;

    @Column(name = "location_id", columnDefinition = "SMALLINT UNSIGNED", nullable = false)
    private Integer locationId;

    @Column(name = "booking_allowed", columnDefinition = "CHAR(1) DEFAULT 'y'")
    private char bookingAllowed;

    @Column(name = "is_approval_needed", columnDefinition = "CHAR(1) DEFAULT 'n'", nullable = false)
    private char isApprovalNeeded;

    @Column(name = "is_active", columnDefinition = "CHAR(1) DEFAULT 'y'", nullable = false)
    private char isActive;

    @Column(name = "recurrence_allowed", columnDefinition = "CHAR(1) DEFAULT 'y'", nullable = false)
    private char recurrenceAllowed;

    @Column(name = "created_by", columnDefinition = "SMALLINT UNSIGNED", nullable = true)
    private Integer createdBy;

    @Column(name = "created_on", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private LocalDateTime createdOn;

    // Getters and Setters
    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public char getExternalDisplayAvailability() {
        return externalDisplayAvailability;
    }

    public void setExternalDisplayAvailability(char externalDisplayAvailability) {
        this.externalDisplayAvailability = externalDisplayAvailability;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public char getBookingAllowed() {
        return bookingAllowed;
    }

    public void setBookingAllowed(char bookingAllowed) {
        this.bookingAllowed = bookingAllowed;
    }

    public char getIsApprovalNeeded() {
        return isApprovalNeeded;
    }

    public void setIsApprovalNeeded(char isApprovalNeeded) {
        this.isApprovalNeeded = isApprovalNeeded;
    }

    public char getIsActive() {
        return isActive;
    }

    public void setIsActive(char isActive) {
        this.isActive = isActive;
    }

    public char getRecurrenceAllowed() {
        return recurrenceAllowed;
    }

    public void setRecurrenceAllowed(char recurrenceAllowed) {
        this.recurrenceAllowed = recurrenceAllowed;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
}
