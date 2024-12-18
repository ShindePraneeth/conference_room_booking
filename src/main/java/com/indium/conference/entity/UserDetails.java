package com.indium.conference.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_details")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", columnDefinition = "SMALLINT UNSIGNED")
    private Integer userId;

    @Column(name = "email_id", columnDefinition = "VARCHAR(300)", nullable = false)
    private String emailId;

    @Column(name = "session_id", columnDefinition = "VARCHAR(300)")
    private String sessionId;

    @Column(name = "role_type", columnDefinition = "VARCHAR(50)")
    private String roleType;

    @Column(name = "preferred_location", columnDefinition = "VARCHAR(50)")
    private String preferredLocation;

    // Getters and Setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getPreferredLocation() {
        return preferredLocation;
    }

    public void setPreferredLocation(String preferredLocation) {
        this.preferredLocation = preferredLocation;
    }
}
