package com.thellai.bookmyshow.repositories;

import com.thellai.bookmyshow.models.Show;
import com.thellai.bookmyshow.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {
    List<ShowSeatType> findAllByShow(Show show);
}
