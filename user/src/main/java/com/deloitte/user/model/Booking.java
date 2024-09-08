package com.deloitte.user.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class Booking {
    private Integer id;

    private Integer userId; // Assuming you store userId or use User enti

    private Integer spotId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double totalAmount;

}
