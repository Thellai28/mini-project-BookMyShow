package com.thellai.bookmyshow.controllers;

import com.thellai.bookmyshow.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
            isTerminated = welcomeUser();
            if( !isTerminated )break;
            optionProvider();
        }
    }



    public boolean welcomeUser(){
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



    public boolean optionProvider(){
        createSpaceInTerminal();
        String optionMessage = "Please choose an option : \n"+
                "1. Book a movie \n" +
                "2. Cancel a movie \n";
        System.out.println(optionMessage);
        int response = sc.nextInt();
        if( response == 1 ){
            bookingController.bookMovie();
        }   return false;
    }



    public void createSpaceInTerminal(){
        for( int i = 0; i < 5; i++ ){
            System.out.println();
        }
    }



}
