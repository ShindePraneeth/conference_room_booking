package com.indium.conference.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_log")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "audit_log_id", columnDefinition = "SMALLINT UNSIGNED")
    private Integer auditLogId;

    @Column(name = "booking_id", columnDefinition = "SMALLINT UNSIGNED", nullable = false)
    private Integer bookingId;

    @Column(name = "created_by", columnDefinition = "VARCHAR(6)", nullable = true)
    private String createdBy;

    @Column(name = "change_type", columnDefinition = "ENUM('modified', 'cancelled')", nullable = false)
    private String changeType;

    @Column(name = "timestamp", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "comments", columnDefinition = "LONGTEXT", nullable = false)
    private String comments;

    // Getters and Setters
    public Integer getAuditLogId() {
        return auditLogId;
    }

    public void setAuditLogId(Integer auditLogId) {
        this.auditLogId = auditLogId;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
