package com.thellai.bookmyshow.controllers;

import com.thellai.bookmyshow.models.Booking;
import com.thellai.bookmyshow.models.ShowSeat;
import com.thellai.bookmyshow.models.User;
import com.thellai.bookmyshow.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;
import java.util.Scanner;

@Controller
public class FrontEndController {
    private boolean isTerminated = false;

    @Autowired
    private UserController userController;


    @Autowired
    private MovieRepository movieRepository;


    @Autowired
    private BookingController bookingController;


    @Autowired
    private LogInController logInController;


    private Scanner sc = new Scanner( System.in );


    // <------------------------------------------METHODS---------------------------------------->

    public void executor(){
        while ( !isTerminated ) {

            Optional<User> fethcedUser= welcomeUser();

            if( fethcedUser.isEmpty() )break; // if the login fails, user will be redirected to the login page :
            int response = optionProvider();

            Booking ticket = directToRespectiveFunction( response, fethcedUser.get());
            if( ticket !=  null ){
                showBookedTicketDetails( ticket );
                isTerminated = true;
            }
        }
    }



    public Optional<User> welcomeUser(){
        createSpaceInTerminal(4);
        String welcomeMessage = "Welcome to bookMyShow :\n" +
                                "Choose one option shown below\n" +
                                "1. Log in\n" +
                                "2. Sign up.\n";
        System.out.println( welcomeMessage );

        int response =  sc.nextInt();
        if( response == 1 ){
            return logInController.logIn();
        }
        return logInController.signup();
    }



    public int optionProvider( ){
        createSpaceInTerminal(3);
        System.out.println("----------------------HOME PAGE ---------------------------------");
        createSpaceInTerminal(2);
        String optionMessage = "Please choose an option : \n"+
                "1. Book a movie \n" +
                "2. Cancel a movie \n";
        System.out.println(optionMessage);
        return sc.nextInt();
    }



    public Booking directToRespectiveFunction( int response, User loggedInUser ){
        if( response == 1 ){
            return proceedWithBooking( loggedInUser );
        }else System.out.println("cancellation module is not implemented");
        return null;
    }



    public void showBookedTicketDetails( Booking ticket ){
        System.out.println("------------- PRINTING BOOKING DETAILS ---------------------------");
        createSpaceInTerminal(5);
        System.out.println("User name: " + ticket.getUser().getName() );
        System.out.println("Move : " + ticket.getShow().getMovie().getName() );
        System.out.println("Amount Paid : " + 250 * (ticket.getShowSeat().size()) );

        System.out.print("Booked Seats : ");

        int seatCount = 0;
        for( ShowSeat currShowSeat : ticket.getShowSeat() ){
            seatCount++;
            int seatNo  = currShowSeat.getSeat().getSeatNO();
            String message = "Seat : " + seatNo;
            if( seatCount == ticket.getShowSeat().size() ){ // if it's a last seat, we have to use period
                message += ". ";
            }else message += ", ";  // or else we should use comma's:

            System.out.print(message);
        }
        System.out.println();

        System.out.println("Payment status : SUCCESS" );

        System.out.println("Enjoy the movie ! Have a nice day, visit again :)");

        createSpaceInTerminal(4);
    }

    public Booking proceedWithBooking( User loggedInUser ){
        Booking bookedTicket  = bookingController.bookMovie( loggedInUser );
        return bookedTicket;
    }



    public void createSpaceInTerminal(int lines ){
        for( int i = 0; i < lines; i++ ){
            System.out.println();
        }
    }

}
