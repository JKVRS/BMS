package com.development.bookmyshow.repository;

import com.development.bookmyshow.model.Show;
import com.development.bookmyshow.model.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShowRepository extends JpaRepository<Show, Long> {


    public Optional<Show> findById(long showId);


}
