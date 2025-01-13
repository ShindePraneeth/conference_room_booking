package com.indium.conference.newentity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "location_details")
public class LocationDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short locationId;

    @Column(nullable = false)
    private String locationName;

    @Column(nullable = false)
    private Character isActive;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    private LocalDateTime createdOn;

    @Column(columnDefinition = "json")
    private String adminUsersId;

    private Short bookingAllowedWindowInDays;
    private Byte noticeDurationToBookInMin;
    private Byte recurrenceCountAllowed;
}
