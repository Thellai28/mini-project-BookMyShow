package com.thellai.bookmyshow.controllers;

import com.thellai.bookmyshow.dtos.ResponseStatus;
import com.thellai.bookmyshow.dtos.SignUpRequestDto;
import com.thellai.bookmyshow.dtos.SignUpResponseDto;
import com.thellai.bookmyshow.models.User;
import com.thellai.bookmyshow.services.UserService;

public class UserController {

    private UserService userService;

    public UserController( UserService userService ) {
        this.userService = userService;
    }

    public SignUpResponseDto signUp( SignUpRequestDto request ){
        SignUpResponseDto response = new SignUpResponseDto();
        User user;

        try{
            user = userService.signup( request.getEmail(), request.getPassword() );
            response.setResponseStatus( ResponseStatus.SUCCESS);
            response.setUserId( user.getId() );
        }catch ( Exception ex ){
            response.setResponseStatus( ResponseStatus.FAILURE );
        }
        return response;

    }
}
