package com.development.bookmyshow.repository;

import com.development.bookmyshow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findById(Long userId);

    public Optional<User> findByEmail(String email);

    public User save(User user);

}
