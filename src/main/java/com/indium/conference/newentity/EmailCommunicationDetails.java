package com.indium.conference.newentity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "email_communication_details")
public class EmailCommunicationDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short emailCommId;

    private String fromUser;

    @Column(columnDefinition = "json")
    private String emailTo;

    @Column(columnDefinition = "json")
    private String emailCc;

    private String subject;

    @Column(columnDefinition = "TEXT")
    private String body;

    private LocalDateTime createdOn;
}
