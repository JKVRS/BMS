package com.development.bookmyshow.repository;

import com.development.bookmyshow.model.Seat;
import com.development.bookmyshow.model.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    public List<Seat> findSeatsByIdIn(List<Long> seatId);


}
