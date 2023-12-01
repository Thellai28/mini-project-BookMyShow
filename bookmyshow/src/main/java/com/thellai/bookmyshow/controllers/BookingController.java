package com.thellai.bookmyshow.controllers;

import com.thellai.bookmyshow.dtos.BookMovieResponseDto;
import com.thellai.bookmyshow.dtos.BookingMovieRequestDto;
import com.thellai.bookmyshow.models.Show;
import com.thellai.bookmyshow.models.ShowSeat;
import com.thellai.bookmyshow.models.User;
import com.thellai.bookmyshow.repositories.ShowRepository;
import com.thellai.bookmyshow.repositories.ShowSeatRepository;
import com.thellai.bookmyshow.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;

import java.util.List;
import java.util.Optional;

@Controller
public class BookingController {

    public BookMovieResponseDto bookMovie( Long userId, List<Long> seatids, long showid ){
        return null;
    }
}
