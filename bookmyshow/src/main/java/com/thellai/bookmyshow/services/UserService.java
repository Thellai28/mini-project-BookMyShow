package com.thellai.bookmyshow.services;

import com.thellai.bookmyshow.exceptions.NullValueFoundInMandatoryFieldException;
import com.thellai.bookmyshow.models.User;
import com.thellai.bookmyshow.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService( UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    public User signup( String name, String email, String password ){
        //check if the user is already there : else create new user
        if( name == null || email == null || password == null ){
            throw new NullValueFoundInMandatoryFieldException("Mandatory Fields cannot be empty, please fill them");
        }

        User newUser = new User();
        newUser.setEmail( email );
        newUser .setPassword( password );
        newUser.setName( name );

        User savedUser = userRepository.save( newUser );
        return savedUser;
    }
    public Optional<User> findUserUsingMailId( String email ){
        Optional<User> fetchedUser = userRepository.findByEmail(email);
        return fetchedUser;
    }

}
