package com.indium.conference.newentity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "room_details")
public class RoomDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short roomId;

    @Column(nullable = false)
    private String roomName;

    @Column(nullable = false)
    private Character externalDisplayAvailability;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String facilities;

    @Column(nullable = false)
    private Short capacity;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private LocationDetails locationId;

    private Character bookingAllowed;
    private Character isApprovalNeeded;
    private Character isActive;
    private Character recurrenceAllowed;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    private LocalDateTime createdOn;
}

