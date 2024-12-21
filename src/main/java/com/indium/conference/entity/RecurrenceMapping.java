package com.indium.conference.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recurrence_mapping")
public class RecurrenceMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recurrence_id", columnDefinition = "SMALLINT UNSIGNED")
    private Integer recurrenceId;

    @Column(name = "booking_id", columnDefinition = "SMALLINT UNSIGNED", nullable = false)
    private Integer bookingId;

    @Column(name = "recurrence_type", columnDefinition = "ENUM('daily', 'weekly')", nullable = false)
    private String recurrenceType;

    @Column(name = "recurrence_end_date", columnDefinition = "DATETIME")
    private LocalDateTime recurrenceEndDate;

    @Column(name = "repeat_on_days", columnDefinition = "JSON")
    private String repeatOnDays;

    // Getters and Setters
    public Integer getRecurrenceId() {
        return recurrenceId;
    }

    public void setRecurrenceId(Integer recurrenceId) {
        this.recurrenceId = recurrenceId;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public String getRecurrenceType() {
        return recurrenceType;
    }

    public void setRecurrenceType(String recurrenceType) {
        this.recurrenceType = recurrenceType;
    }

    public LocalDateTime getRecurrenceEndDate() {
        return recurrenceEndDate;
    }

    public void setRecurrenceEndDate(LocalDateTime recurrenceEndDate) {
        this.recurrenceEndDate = recurrenceEndDate;
    }

    public String getRepeatOnDays() {
        return repeatOnDays;
    }

    public void setRepeatOnDays(String repeatOnDays) {
        this.repeatOnDays = repeatOnDays;
    }
}
