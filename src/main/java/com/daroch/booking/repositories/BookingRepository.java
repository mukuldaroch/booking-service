package com.daroch.booking.repositories;

import com.daroch.booking.domain.entities.Booking;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {}
