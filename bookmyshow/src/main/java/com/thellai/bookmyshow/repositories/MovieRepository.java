package com.thellai.bookmyshow.repositories;

import com.thellai.bookmyshow.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository< Movie, Long> {

    @Override
    List<Movie> findAll();
}
