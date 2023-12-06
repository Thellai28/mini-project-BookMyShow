package com.thellai.bookmyshow;

import com.thellai.bookmyshow.controllers.FrontEndController;
import com.thellai.bookmyshow.controllers.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Scanner;

@EnableJpaAuditing // This annotation is mostly used in configuration or SpringBootApplication class like this
// The main purpose of this annotation in this code is to actively modify the dates.
@SpringBootApplication
public class BookMyShowApplication implements CommandLineRunner {
	@Autowired
	private UserController userController;
	@Autowired
	private FrontEndController frontEndController;
	private Scanner sc = new Scanner( System.in );

	public static void main(String[] args) {
		SpringApplication.run(BookMyShowApplication.class, args);
	}

	@Override
	public void run( String... args ) throws Exception {
			frontEndController.executor();
	}
}

/*
	In Spring Boot, CommandLineRunner is an interface that provides a way to run code after the Spring application
	has started up. It allows you to execute custom logic when the application context is fully loaded and the
	application is ready to process requests. The run method of the CommandLineRunner interface is called
	automatically by the Spring Boot framework.

	In the context of the code snippet you provided, the use of CommandLineRunner is to perform specific tasks or
	actions when the Spring Boot application starts up.
*/
