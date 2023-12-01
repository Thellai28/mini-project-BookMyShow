package com.thellai.bookmyshow.repositories;

import com.thellai.bookmyshow.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    @Override
    List<ShowSeat> findAllById( Iterable<Long> longs );
    ShowSeat save(ShowSeat showSeat);
}
