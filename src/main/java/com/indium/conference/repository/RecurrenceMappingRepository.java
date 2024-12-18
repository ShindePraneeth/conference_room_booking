package com.indium.conference.repository;

import com.indium.conference.entity.RecurrenceMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// Repository for RecurrenceMapping
@Repository
public interface RecurrenceMappingRepository extends JpaRepository<RecurrenceMapping, Integer> {
    @Query("SELECT r FROM RecurrenceMapping r WHERE r.bookingId = :bookingId")
    List<RecurrenceMapping> findRecurrenceByBookingId(@Param("bookingId") Integer bookingId);
}

