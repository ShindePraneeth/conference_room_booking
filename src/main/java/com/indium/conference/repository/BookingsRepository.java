package com.indium.conference.repository;

import com.indium.conference.entity.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

// Repository for Bookings
@Repository
public interface BookingsRepository extends JpaRepository<Bookings, Integer> {
    @Query("SELECT b FROM Bookings b WHERE b.userId = :userId")
    List<Bookings> findBookingsByUserId(@Param("userId") Integer userId);

    @Query("SELECT b FROM Bookings b WHERE b.status = 'awaiting_approval'")
    List<Bookings> findPendingApprovalBookings();

    @Query("SELECT b FROM Bookings b WHERE b.eventDate BETWEEN :startDate AND :endDate")
    List<Bookings> findBookingsWithinDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}

