package com.deloitte.parking.repository;

import com.deloitte.parking.entity.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot,Integer> {

    List<ParkingSpot> findBySpotStatus(ParkingSpot.SpotStatus spotStatus);
}
