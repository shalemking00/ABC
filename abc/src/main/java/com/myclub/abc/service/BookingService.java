package com.myclub.abc.service;

import com.myclub.abc.model.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    public Booking createBooking(Booking booking);

    public List<Booking> searchBookings(String memberName, LocalDate startDate, LocalDate endDate);


}
