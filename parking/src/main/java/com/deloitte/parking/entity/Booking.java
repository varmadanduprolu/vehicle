package com.deloitte.parking.entity;

import com.deloitte.parking.model.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Integer userId; // Assuming you store userId or use User enti
    @ManyToOne
    @JoinColumn(name = "parking_spot_id")
    private Integer spotId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double totalAmount
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    public enum BookingStatus {
        BOOKED,
        PENDING,
        CANCELLED
    }
}
