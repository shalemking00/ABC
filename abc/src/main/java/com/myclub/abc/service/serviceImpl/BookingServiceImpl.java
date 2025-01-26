package com.myclub.abc.service.serviceImpl;

import com.myclub.abc.model.Booking;
import com.myclub.abc.model.ClassEntity;
import com.myclub.abc.repository.BookingRepository;
import com.myclub.abc.repository.ClassRepository;
import com.myclub.abc.service.BookingService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final ClassRepository classRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, ClassRepository classRepository) {
        this.bookingRepository = bookingRepository;
        this.classRepository = classRepository;
    }


    @Override
    public Booking createBooking(Booking booking) {
        ClassEntity classEntity = classRepository.findById(booking.getClassEntity().getId())
                .orElseThrow(() -> new IllegalArgumentException("Class not found"));

        if (booking.getParticipationDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Participation date must be in the future");
        }

        int availableSpots = classEntity.getDailyAvailability()
                .getOrDefault(booking.getParticipationDate(), 0);
        if (availableSpots <= 0) {
            throw new IllegalArgumentException("Class is full on the selected date");
        }

        classEntity.getDailyAvailability().put(booking.getParticipationDate(), availableSpots - 1);
        classRepository.save(classEntity);

        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> searchBookings(String memberName, LocalDate startDate, LocalDate endDate) {
        if (memberName != null && startDate != null && endDate != null) {
            return bookingRepository.findByMemberNameAndParticipationDateBetween(memberName, startDate, endDate);
        } else if (memberName != null) {
            return bookingRepository.findByMemberName(memberName);
        } else if (startDate != null && endDate != null) {
            return bookingRepository.findByParticipationDateBetween(startDate, endDate);
        } else {
            return bookingRepository.findAll();
        }
    }
}
