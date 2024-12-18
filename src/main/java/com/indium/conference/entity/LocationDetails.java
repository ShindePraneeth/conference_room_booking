package com.indium.conference.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "location_details")
public class LocationDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id", columnDefinition = "SMALLINT UNSIGNED")
    private Integer locationId;

    @Column(name = "location_name", columnDefinition = "VARCHAR(50)", nullable = false)
    private String locationName;

    @Column(name = "is_active", columnDefinition = "CHAR(1)", nullable = false)
    private char isActive;

    @Column(name = "created_by", columnDefinition = "SMALLINT UNSIGNED")
    private Integer createdBy;

    @Column(name = "created_on", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime createdOn;

    @Column(name = "admin_users_id", columnDefinition = "JSON")
    private String adminUsersId;

    @Column(name = "booking_allowed_window_in_days", columnDefinition = "SMALLINT UNSIGNED")
    private Integer bookingAllowedWindowInDays;

    @Column(name = "notice_duration_to_book_in_min", columnDefinition = "TINYINT UNSIGNED")
    private Integer noticeDurationToBookInMin;

    @Column(name = "recurrence_count_allowed", columnDefinition = "TINYINT UNSIGNED")
    private Integer recurrenceCountAllowed;

    // Getters and Setters
    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public char getIsActive() {
        return isActive;
    }

    public void setIsActive(char isActive) {
        this.isActive = isActive;
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

    public String getAdminUsersId() {
        return adminUsersId;
    }

    public void setAdminUsersId(String adminUsersId) {
        this.adminUsersId = adminUsersId;
    }

    public Integer getBookingAllowedWindowInDays() {
        return bookingAllowedWindowInDays;
    }

    public void setBookingAllowedWindowInDays(Integer bookingAllowedWindowInDays) {
        this.bookingAllowedWindowInDays = bookingAllowedWindowInDays;
    }

    public Integer getNoticeDurationToBookInMin() {
        return noticeDurationToBookInMin;
    }

    public void setNoticeDurationToBookInMin(Integer noticeDurationToBookInMin) {
        this.noticeDurationToBookInMin = noticeDurationToBookInMin;
    }

    public Integer getRecurrenceCountAllowed() {
        return recurrenceCountAllowed;
    }

    public void setRecurrenceCountAllowed(Integer recurrenceCountAllowed) {
        this.recurrenceCountAllowed = recurrenceCountAllowed;
    }
}
