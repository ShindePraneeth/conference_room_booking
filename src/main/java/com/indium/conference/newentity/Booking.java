package com.indium.conference.newentity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short bookingId;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private LocationDetails locationId;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private RoomDetails roomId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    private LocalDateTime bookedOn;

    @Column(nullable = false)
    private LocalDate eventDate;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private String eventName;

    @Column(name = "status", columnDefinition = "ENUM('awaiting_approval', 'approved', 'cancelled', 'rejected')", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "approved_by")
    private User approvedBy;

    private LocalDateTime approvedOn;
    private String approvalRemarks;
    private Character isRecurring;
    private Short recurrenceId;
}

