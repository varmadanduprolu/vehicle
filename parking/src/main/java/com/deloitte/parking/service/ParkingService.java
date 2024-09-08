package com.deloitte.parking.service;


import com.deloitte.parking.entity.ParkingSpot;
import com.deloitte.parking.repository.BookingRepository;
import com.deloitte.parking.repository.ParkingSpotRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deloitte.parking.entity.Booking;


import java.time.Duration;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class ParkingService {
    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private BookingRepository bookingRepository;

    // Get all available parking spots
    public List<ParkingSpot> getAvailableSpots() {
        return parkingSpotRepository.findBySpotStatus(ParkingSpot.SpotStatus.AVAILABLE);
    }

    // Book a parking spot
    @Transactional
    public Booking bookParkingSpot(Long spotId, Long userId, LocalDateTime startTime, LocalDateTime endTime) {
        ParkingSpot parkingSpot = parkingSpotRepository.findById(Math.toIntExact(spotId))
                .orElseThrow(() -> new RuntimeException("Parking spot not found"));

        if (parkingSpot.getSpotStatus() == ParkingSpot.SpotStatus.OCCUPIED) {
            throw new RuntimeException("Parking spot is already occupied");
        }

        parkingSpot.setSpotStatus(ParkingSpot.SpotStatus.OCCUPIED);
        parkingSpotRepository.save(parkingSpot);

        Booking booking = new Booking();
        booking.setParkingSpot(parkingSpot);
        booking.setUserId(Math.toIntExact(userId));
        booking.setStartTime(startTime);
        booking.setEndTime(endTime);
        booking.setBookingStatus(Booking.BookingStatus.BOOKED);

        double duration = Duration.between(startTime, endTime).toHours();
        booking.setTotalAmount(duration * parkingSpot.getPrice());

        return bookingRepository.save(booking);
    }

    // Cancel a booking
    @Transactional
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(Math.toIntExact(bookingId))
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setBookingStatus(Booking.BookingStatus.CANCELLED);
        bookingRepository.save(booking);

        ParkingSpot spot = booking.getParkingSpot();
        spot.setSpotStatus(ParkingSpot.SpotStatus.AVAILABLE);
        parkingSpotRepository.save(spot);
    }

    // Update parking spot status
    public ParkingSpot updateParkingSpotStatus(Long spotId, ParkingSpot.SpotStatus spotStatus) {
        ParkingSpot spot = parkingSpotRepository.findById(Math.toIntExact(spotId))
                .orElseThrow(() -> new RuntimeException("Parking spot not found"));
        spot.setSpotStatus(spotStatus);
        return parkingSpotRepository.save(spot);
    }

    // View all bookings by user
    public List<Booking> viewBookingsByUser(Long userId) {
        return bookingRepository.findById(userId);
    }
}
