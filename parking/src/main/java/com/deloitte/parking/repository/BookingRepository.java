package com.deloitte.parking.repository;

import com.deloitte.parking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Integer> {
    List<Booking> findById(Long userId);
}
