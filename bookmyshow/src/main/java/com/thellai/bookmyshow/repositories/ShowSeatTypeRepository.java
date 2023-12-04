package com.thellai.bookmyshow.repositories;

import com.thellai.bookmyshow.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {

    @Override
    List<ShowSeatType> findAll();
}
