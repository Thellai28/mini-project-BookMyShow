package com.thellai.bookmyshow.services;

import com.thellai.bookmyshow.models.User;
import com.thellai.bookmyshow.repositories.UserRepository;

public class UserService {
    private UserRepository userRepository;

    public UserService( UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    public User signup( String email, String password ){
        //check if the user is already there : else create new user

        User newUser = new User();
        newUser.setEmail( email );
        newUser .setPassword( password );

        User savedUser = userRepository.save( newUser );
        return savedUser;
    }
}
