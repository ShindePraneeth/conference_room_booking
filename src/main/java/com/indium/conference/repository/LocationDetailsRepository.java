package com.indium.conference.repository;

import com.indium.conference.entity.LocationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// Repository for LocationDetails
@Repository
public interface LocationDetailsRepository extends JpaRepository<LocationDetails, Integer> {
    @Query("SELECT l FROM LocationDetails l WHERE l.isActive = 'y'")
    List<LocationDetails> findActiveLocations();
}
