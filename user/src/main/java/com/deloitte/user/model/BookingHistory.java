package com.deloitte.user.model;

import lombok.Data;

import java.util.List;

@Data
public class BookingHistory {
    private List<Booking> bookingList;
}
