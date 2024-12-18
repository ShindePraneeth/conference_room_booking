package com.indium.conference.repository;

import com.indium.conference.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// Repository for UserDetails
@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {
    @Query("SELECT u FROM UserDetails u WHERE u.emailId = :emailId")
    UserDetails findByEmailId(@Param("emailId") String emailId);
}