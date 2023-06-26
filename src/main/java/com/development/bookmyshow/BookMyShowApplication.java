package com.development.bookmyshow;

import com.development.bookmyshow.controller.UserController;
import com.development.bookmyshow.dto.UserRequestDto;
import com.development.bookmyshow.dto.UserResponseDto;
import com.development.bookmyshow.model.Movie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Scanner;

@SpringBootApplication
@EnableJpaAuditing
public class BookMyShowApplication implements CommandLineRunner {

	UserController userController;
	Scanner sc = new Scanner(System.in);

	public BookMyShowApplication(UserController userController) {

		this.userController = userController;
	}

	public static void main(String[] args) {

		SpringApplication.run(BookMyShowApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		UserRequestDto userRequestDto = new UserRequestDto();
		userRequestDto.setName("Vijay");
		userRequestDto.setEmail("abc@gmail.com");
		userRequestDto.setPassword("password");
		userRequestDto.setAge(23);

		userRequestDto.setName("Ajay");
		userRequestDto.setEmail("xyz@gmail.com");
		userRequestDto.setPassword("password1");
		userRequestDto.setAge(24);

		UserResponseDto userResponseDto = userController.singUpUser(userRequestDto);
		System.out.println(userResponseDto.getResponseStatus() + " :- " + userResponseDto.getResponseMessage());

		System.out.println("Do you want to login ?");
		String choice = sc.next();
		if (choice.equalsIgnoreCase("y")) {
			UserRequestDto userRequestDtoLogin = new UserRequestDto();
			userRequestDtoLogin.setEmail(sc.next());
			userRequestDtoLogin.setPassword(sc.next());
			UserResponseDto userResponseDtoLogin = userController.loginUser(userRequestDtoLogin);
			System.out.println(userResponseDtoLogin.getResponseStatus() + " :- " + userResponseDtoLogin.getResponseMessage());
		}

	}
}
