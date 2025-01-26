package com.myclub.abc.service;

import com.myclub.abc.model.Booking;
import com.myclub.abc.model.ClassEntity;
import com.myclub.abc.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookingServiceTest {
    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBooking() {
        ClassEntity classEntity = new ClassEntity();
        classEntity.setId(1L);

        Booking booking = new Booking();
        booking.setMemberName("Alice");
        booking.setClassEntity(classEntity);
        booking.setParticipationDate(LocalDate.of(2025, 2, 5));

        when(bookingRepository.save(booking)).thenReturn(booking);

        Booking createdBooking = bookingService.createBooking(booking);

        assertNotNull(createdBooking);
        assertEquals("Alice", createdBooking.getMemberName());
        verify(bookingRepository, times(1)).save(booking);
    }

    @Test
    void testFindBookingsByMember() {
        Booking booking = new Booking();
        booking.setMemberName("Alice");

        when(bookingRepository.findByMemberName("Alice")).thenReturn(List.of(booking));

        List<Booking> bookings = bookingService.searchBookings("Alice",null,null);

        assertFalse(bookings.isEmpty());
        assertEquals("Alice", bookings.get(0).getMemberName());
        verify(bookingRepository, times(1)).findByMemberName("Alice");
    }

}
