package com.myclub.abc.repository;

import com.myclub.abc.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findByMemberNameAndParticipationDateBetween(String memberName, LocalDate startDate, LocalDate endDate);

    List<Booking> findByMemberName(String memberName);

    List<Booking> findByParticipationDateBetween(LocalDate startDate, LocalDate endDate);

}
