package com.thellai.bookmyshow.services;

import com.thellai.bookmyshow.models.Show;
import com.thellai.bookmyshow.models.ShowSeat;
import com.thellai.bookmyshow.models.ShowSeatType;
import com.thellai.bookmyshow.repositories.ShowSeatRepository;
import com.thellai.bookmyshow.repositories.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PricingService {

    private ShowSeatTypeRepository showSeatTypeRepository;

    public int calculatePrice( List<ShowSeat> showSeats, Show show ){
        List<ShowSeatType> showSeatTypes =  showSeatTypeRepository.findAllByShow( show );

        int amount = 0;

        for( ShowSeat showSeat : showSeats ){
            for( ShowSeatType showSeatType : showSeatTypes ){

                if( showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType()) ){
                    amount += showSeatType.getPrice();
                    break; // one use will choose one type of seat, since we found the seat there is no meaning to
                    // iterate through the rest of the list :
                }
            }
        }
        return amount;
    }
}
