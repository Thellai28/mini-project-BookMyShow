package com.thellai.bookmyshow;

import com.thellai.bookmyshow.controllers.UserController;
import com.thellai.bookmyshow.dtos.SignUpRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookMyShowApplication implements CommandLineRunner {
	@Autowired
	private UserController userController;

	public static void main(String[] args) {
		SpringApplication.run(BookMyShowApplication.class, args);
	}

	@Override
	public void run( String... args ) throws Exception {
		SignUpRequestDto signUpRequestDto =  new SignUpRequestDto();
		signUpRequestDto.setEmail("thellai@gmail.com");
		signUpRequestDto.setPassword("Password");

		userController.signUp( signUpRequestDto );

	}
}
