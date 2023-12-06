package com.thellai.bookmyshow.services;

import com.thellai.bookmyshow.models.ShowSeat;
import com.thellai.bookmyshow.models.ShowSeatType;
import com.thellai.bookmyshow.repositories.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricingService {

    @Autowired
    private ShowSeatTypeRepository showSeatTypeRepository;


    public int calculatePrice(  List<ShowSeat> finalSelectedSeatsForBooking ){
        int amount = 0;
        List<ShowSeatType> allShowSeatTypes = showSeatTypeRepository.findAll();

        for( ShowSeat currShowSeat : finalSelectedSeatsForBooking ){
            for( ShowSeatType currShowSeatType : allShowSeatTypes ){
                if( currShowSeat.getSeat().getSeatType().getPrice() == currShowSeatType.getSeatType().getPrice() ){
                    amount += currShowSeatType.getPrice();
                    break; // One seat can have only one type, we found that type, no need to iterate through
                    // the rest of the list :
                }
            }
        }

        return amount;
    }
}
