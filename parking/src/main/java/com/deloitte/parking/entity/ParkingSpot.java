package com.deloitte.parking.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer spotId;
    private String location;
    private Integer spotNumber;
    private Double price;
    @Enumerated(EnumType.STRING)
    private SpotStatus spotStatus;

    public enum SpotStatus {
        AVAILABLE,
        OCCUPIED
    }
}
