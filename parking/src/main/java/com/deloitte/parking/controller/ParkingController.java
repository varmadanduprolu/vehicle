package com.deloitte.parking.controller;


import com.deloitte.parking.entity.Booking;
import com.deloitte.parking.entity.ParkingSpot;
import com.deloitte.parking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {
    @Autowired
    private ParkingService parkingService;

    @GetMapping("/spots/available")
    public List<ParkingSpot> getAvailableParkingSpots() {
        return parkingService.getAvailableSpots();
    }

    @PostMapping("/book")
    public Booking bookParkingSpot(@RequestParam Long spotId,
                                   @RequestParam Long userId,
                                   @RequestParam String startTime,
                                   @RequestParam String endTime) {
        return parkingService.bookParkingSpot(spotId, userId,
                LocalDateTime.parse(startTime), LocalDateTime.parse(endTime));
    }

    @PostMapping("/cancel/{bookingId}")
    public String cancelBooking(@PathVariable Long bookingId) {
        parkingService.cancelBooking(bookingId);
        return "Booking cancelled successfully";
    }

    @PatchMapping("/spots/{spotId}/status")
    public ParkingSpot updateParkingSpotStatus(@PathVariable Long spotId,
                                               @RequestParam ParkingSpot.SpotStatus spotStatus) {
        return parkingService.updateParkingSpotStatus(spotId, spotStatus);
    }

    @GetMapping("/user/{userId}/bookingsHistory")
    public List<Booking> getUserBookings(@PathVariable Long userId) {
        return parkingService.viewBookingsByUser(userId);
    }

}
