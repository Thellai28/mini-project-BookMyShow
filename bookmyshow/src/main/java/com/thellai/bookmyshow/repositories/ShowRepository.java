package com.thellai.bookmyshow.repositories;

import com.thellai.bookmyshow.models.Show;
import com.thellai.bookmyshow.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShowRepository extends JpaRepository<Show, Long> {
    @Override
    Optional<Show> findById( Long aLong );

}
