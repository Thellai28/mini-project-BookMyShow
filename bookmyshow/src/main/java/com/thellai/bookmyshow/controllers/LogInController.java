package com.thellai.bookmyshow.controllers;

import com.thellai.bookmyshow.models.User;
import com.thellai.bookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;
import java.util.Scanner;

@Controller
public class LogInController {
    private boolean isTerminated = false;
    private Scanner sc = new Scanner( System.in );

    @Autowired
    private UserRepository userRepository;

    public Optional<User> logIn(){
        createSpaceInTerminal();
        System.out.println("Enter your email id : ");
        String email = sc.next();

        System.out.println("Enter your password : ");
        String password = sc.next();

        Optional<User> fetchedUser = userRepository.findByEmail( email );

        if ( fetchedUser.isEmpty() ){
            System.out.println("No User Found");
        }else{
            User existingUser = fetchedUser.get();
            if( email.equals( existingUser.getEmail() ) && password.equals( existingUser.getPassword())){
                System.out.println("Log in Successful");
            }else System.out.println("log in Failed, enter valid email & password");
        }
        return fetchedUser;
    }
    public Optional<User> signup(){
        createSpaceInTerminal();
        System.out.println("Enter your name : ");
        String name = sc.next();

        System.out.println("Enter your email id : ");
        String email = sc.next();

        System.out.println("Enter your password : ");
        String password = sc.next();

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);

        // Before saving, first we have to check if there is no other user logged in or not.

        userRepository.save( newUser );

        System.out.println("sign in successful !");
        return Optional.ofNullable(newUser);
    }

    public void createSpaceInTerminal(){
        for( int i = 0; i < 4; i++ ){
            System.out.println();
        }
    }
}
