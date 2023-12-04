package com.thellai.bookmyshow.controllers;


import com.thellai.bookmyshow.models.Payment;
import com.thellai.bookmyshow.models.PaymentProvider;
import com.thellai.bookmyshow.models.ShowSeat;
import com.thellai.bookmyshow.models.ShowSeatStatus;
import com.thellai.bookmyshow.repositories.PaymentRepository;
import com.thellai.bookmyshow.repositories.ShowSeatRepository;
import com.thellai.bookmyshow.services.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;

@Controller
public class PaymentController {
    private Scanner sc = new Scanner( System.in);

    @Autowired
    private PricingService pricingService;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public void initializePaymentProcess( List<ShowSeat> finalSelectedSeatsForBooking ){
        int amount = pricingService.calculatePrice(finalSelectedSeatsForBooking);
        int response = showPaymentOptions();
        boolean isPaid = requestMoneyTransfer( response, amount );
        if( !isPaid ){
            System.out.println("Transaction failed, unable to book tickets.");
            return;
        }
        changeShowSeatStatus(finalSelectedSeatsForBooking);
    }


    private int showPaymentOptions(){
        createSpaceInTerminal(2);
        String optionMessage = "Please select your preferred payment mode \n"
                            + "1. Upi \n"
                            + "2. Card \n"
                            + "3. Net banking";
        System.out.println( optionMessage );
        createSpaceInTerminal(2);

        int response = sc.nextInt();
        return response;
    }





    private boolean requestMoneyTransfer( int preferredModeOfPayment, int amount){
        createSpaceInTerminal(2);
        if( preferredModeOfPayment == 1 ){
            System.out.println("Initializing money request via UPI...");
            printMoneyTransactionMessages( amount );
            return true;
        }else if( preferredModeOfPayment == 2 ){
            System.out.println("Initializing money request via Card...");
            printMoneyTransactionMessages( amount );
            return true;
        }else if( preferredModeOfPayment == 3 ){
            System.out.println("Initializing money request via net Banking...");
            printMoneyTransactionMessages( amount );
            return true;
        }
        return false;
    }


    private void savePaymentDetails( int amount, int response ){
        Payment newPayment = new Payment();
        newPayment.setAmount( amount );

        PaymentProvider pProvider  = PaymentProvider.UPI;
        if( response == 2 ) pProvider = PaymentProvider.CARD;
        else if ( response ==3) pProvider = PaymentProvider.NET_BANKING;
        else pProvider = PaymentProvider.unKnown;

        newPayment.setPaymentProvider(pProvider);
        paymentRepository.save( newPayment );
    }

    private void changeShowSeatStatus( List<ShowSeat> showSeats){
        for( ShowSeat currShowSeat : showSeats ){
            currShowSeat.setShowSeatStatus(ShowSeatStatus.BOOKED);
            showSeatRepository.save( currShowSeat );
        }
    }






    private void printMoneyTransactionMessages( int amount ){
        System.out.println("Payment Successful, received " + amount);
        System.out.println("Enjoy the movie !!");
    }




    public void createSpaceInTerminal(int lines){
        for( int i = 0; i < lines; i++ ){
            System.out.println();
        }
    }
}
