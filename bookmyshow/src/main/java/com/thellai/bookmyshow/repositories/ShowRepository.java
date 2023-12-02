package com.thellai.bookmyshow.repositories;

import com.thellai.bookmyshow.models.Movie;
import com.thellai.bookmyshow.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    @Override
    Optional<Show> findById( Long aLong );

    Show findByMovie( Movie movie );

}
