package com.indium.conference.newentity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "recurrence_mapping")
public class RecurrenceMappingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short recurrenceId;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking bookingId;

    @Column(name = "recurrence_type", columnDefinition = "ENUM('daily','weekly')", nullable = false)
    private String recurrenceType;

    private LocalDateTime recurrenceEndDate;

    @Column(columnDefinition = "json")
    private String repeatOnDays;
}
