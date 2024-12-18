package com.indium.conference.repository;
import com.indium.conference.entity.RoomDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface RoomDetailsRepository extends JpaRepository<RoomDetails, Integer> {
    @Query("SELECT r FROM RoomDetails r WHERE r.isActive = 'y' AND r.bookingAllowed = 'y'")
    List<RoomDetails> findByIsActiveAndBookingAllowed(char isActive, char bookingAllowed);

    @Query("SELECT r FROM RoomDetails r WHERE r.locationId = :locationId AND r.isActive = 'y'")
    List<RoomDetails> findActiveRoomsByLocation(@Param("locationId") Integer locationId);
}
