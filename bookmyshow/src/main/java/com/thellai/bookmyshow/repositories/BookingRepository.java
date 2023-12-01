package com.thellai.bookmyshow.repositories;

import com.thellai.bookmyshow.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Override
    Optional<Booking> findById( Long aLong);

    @Override
    Booking save(Booking booking);


}
