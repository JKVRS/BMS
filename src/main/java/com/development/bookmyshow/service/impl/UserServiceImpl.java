package com.development.bookmyshow.service.impl;

import com.development.bookmyshow.exception.InvalidArgumentException;
import com.development.bookmyshow.model.User;
import com.development.bookmyshow.repository.UserRepository;
import com.development.bookmyshow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //    validate if user's email is already exist or not
    private boolean isUserExist(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return true;
        }
        return false;

    }


    @Override
    public User singUpUser(String name, String email, String password, int age) throws InvalidArgumentException {
//        validate user if user is already exist or not
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User();
        if (!isUserExist(email)) {
            throw new InvalidArgumentException("User's email id already exist");
        }

        user.setName(name);
        user.setEmail(email);
        user.setPassword(encoder.encode(password));
        user.setAge(age);

        User savedUser = userRepository.save(user);
        return savedUser;
    }

    boolean matchEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return false;
        }
        User user = userOptional.get();
        String alreadySavedEmailInDB = user.getEmail();
        if (!email.equals(alreadySavedEmailInDB)) return false;
        return true;
    }

    //   validate password if password matched or not while login
    boolean matchPassword(String email, String password) {

        Optional<User> userOptional = userRepository.findByEmail(email);
        User user = userOptional.get();
        String userSavedPasswordInDB = user.getPassword();
        PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if (!bCryptPasswordEncoder.matches(password, userSavedPasswordInDB)) {
            return false;
        }
        return true;

    }

    @Override
    public void loginUser(String email, String password) throws InvalidArgumentException {
//         validate the email and password form data base

        if (!matchEmail(email)) {
            throw new InvalidArgumentException("Email doesn't match ");
        }
        if (!matchPassword(email, password)) {
            throw new InvalidArgumentException("Password doesn't match");
        }

    }
}
