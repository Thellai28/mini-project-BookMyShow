package com.thellai.bookmyshow.controllers;

import com.thellai.bookmyshow.models.Booking;
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
            if( fethcedUser.isEmpty() )break;
            int response = optionProvider();
            directToRespectiveFunction( response);
        }
    }



    public Optional<User> welcomeUser(){
        createSpaceInTerminal();
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
        createSpaceInTerminal();
        String optionMessage = "Please choose an option : \n"+
                "1. Book a movie \n" +
                "2. Cancel a movie \n";
        System.out.println(optionMessage);
        return sc.nextInt();
    }



    public void directToRespectiveFunction( int response, User loggedInUser ){
        if( response == 1 ){
            proceedWithBooking( loggedInUser );
        }else System.out.println("cancellation module is not implemented");

    }

    public Booking proceedWithBooking( User loggedInUser ){
        bookingController.bookMovie( loggedInUser );
        return null;
    }



    public void createSpaceInTerminal(){
        for( int i = 0; i < 5; i++ ){
            System.out.println();
        }
    }



}
