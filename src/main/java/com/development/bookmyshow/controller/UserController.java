package com.development.bookmyshow.controller;

import com.development.bookmyshow.dto.UserRequestDto;
import com.development.bookmyshow.dto.UserResponseDto;
import com.development.bookmyshow.exception.InvalidArgumentException;
import com.development.bookmyshow.model.User;
import com.development.bookmyshow.service.UserService;
import com.development.bookmyshow.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

    public UserResponseDto singUpUser(UserRequestDto requestDto) {
        UserResponseDto userResponseDto = new UserResponseDto();
        User user = new User();
        try {
            user = userService.singUpUser(requestDto.getName(),
                    requestDto.getEmail(),
                    requestDto.getPassword(),
                    requestDto.getAge());

            userResponseDto.setEmail(user.getEmail());
            userResponseDto.setResponseStatus("1");
            userResponseDto.setResponseMessage(user.getEmail() + " Successfully singed up.");
        } catch (InvalidArgumentException e) {
            userResponseDto.setResponseStatus("-1");
            userResponseDto.setResponseMessage("Failed to sing up. " + user.getEmail() + " email id already exist.");
        }

        return userResponseDto;
    }

    public UserResponseDto loginUser(UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = new UserResponseDto();
        try {
            userService.loginUser(userRequestDto.getEmail(), userRequestDto.getPassword());
            userResponseDto.setResponseStatus("100");
            userResponseDto.setResponseMessage("User successfully loged in");
        } catch (InvalidArgumentException e) {
            userResponseDto.setResponseStatus("-100");
            userResponseDto.setResponseMessage("Invalid Credentials");

        }
        return userResponseDto;
    }
}
