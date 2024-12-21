package com.indium.conference.repository;

import com.indium.conference.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Integer> {
    @Query("SELECT a FROM AuditLog a WHERE a.bookingId = :bookingId")
    List<AuditLog> findLogsByBookingId(@Param("bookingId") Integer bookingId);

    List<AuditLog> findByBookingId(Integer bookingId);
}
