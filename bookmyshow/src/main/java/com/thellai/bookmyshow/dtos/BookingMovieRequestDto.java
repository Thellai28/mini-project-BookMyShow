package com.thellai.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BookingMovieRequestDto {
    private Long userId;

    private Long showId;

    private List<Long> showSeatIds;
}
