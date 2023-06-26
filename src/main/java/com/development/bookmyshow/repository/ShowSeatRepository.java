package com.development.bookmyshow.repository;

import com.development.bookmyshow.model.Seat;
import com.development.bookmyshow.model.Show;
import com.development.bookmyshow.model.ShowSeat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {


    //    it a DB lock this will lock row level
    @Lock(LockModeType.PESSIMISTIC_WRITE)

    public List<ShowSeat> findAllBySeatInAndShow(List<Seat> seats, Show show);
    //  for create new record and update the record there is same method in spring jpa that is save
//    if parameter has id means it is updating the record otherwise it will save new record.

    public ShowSeat save(ShowSeat showSeat);
}
