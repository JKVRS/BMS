package com.development.bookmyshow.service;

import com.development.bookmyshow.exception.InvalidArgumentException;
import com.development.bookmyshow.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public User singUpUser(String name, String email, String password, int age) throws InvalidArgumentException;

    public void loginUser(String email, String password) throws InvalidArgumentException;
}
