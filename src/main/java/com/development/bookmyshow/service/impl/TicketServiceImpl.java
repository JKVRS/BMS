package com.development.bookmyshow.service.impl;

import com.development.bookmyshow.exception.InvalidArgumentException;
import com.development.bookmyshow.exception.NoSeatsAvailable;
import com.development.bookmyshow.model.*;
import com.development.bookmyshow.repository.*;
import com.development.bookmyshow.service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final SeatRepository seatRepository;

    private final ShowSeatRepository showSeatRepository;

    private final ShowRepository showRepository;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private Date seatLockTime;

    @Autowired
    public TicketServiceImpl(SeatRepository seatRepository, ShowSeatRepository showSeatRepository,
                             ShowRepository showRepository,
                             UserRepository userRepository,
                             TicketRepository ticketRepository) {
        this.seatRepository = seatRepository;
        this.showSeatRepository = showSeatRepository;
        this.showRepository = showRepository;
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;

    }


    @Override

    public Ticket bookTicket(List<Long> seatId, long showId, long userId) throws InvalidArgumentException, NoSeatsAvailable {
//     1. we will find the all the show-seat based on the seatId (getSeatsById())
//     2. we will check the status of the show-seat getShowSeatForSeats(seats)
//     3. if seat is available :-
//     4. lock the seat
//     5. create ticket object.
//     6. if some seat are available than throw an exception.

        List<Seat> seats = seatRepository.findSeatsByIdIn(seatId);
        Optional<Show> showOptional = showRepository.findById(showId);
        if (showOptional.isEmpty()) {
//          throw an exception
            throw new InvalidArgumentException(" Show By: " + showId + " doesn't Exist");
        }

//    get seats for that particular show id:-
        List<ShowSeat> showSeats = getAndLockShowSeat(seats, showOptional);


        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new InvalidArgumentException("User with user id " + userId + " doesn't exist ");
        }

//        crate ticket object and return it
        Ticket ticket = new Ticket();
        ticket.setTicketBookingDate(new Date());
        ticket.setTicketStatus(TicketStatus.PROCESSING);
        ticket.setShow(showOptional.get());
        ticket.setSeatList(seats);
        ticket.setUser(userOptional.get());
        ticket.setAmount(0);

        Ticket savedTicket = ticketRepository.save(ticket);
        return savedTicket;

    }

    @Transactional(isolation = Isolation.SERIALIZABLE, timeout = 5)
    public List<ShowSeat> getAndLockShowSeat(List<Seat> seats, Optional<Show> showOptional) throws NoSeatsAvailable {

        List<ShowSeat> showSeats = showSeatRepository.findAllBySeatInAndShow(seats, showOptional.get());
// now check that all those seat is available or not
        for (ShowSeat showseat : showSeats) {
            if (!showseat.getSeatStatus().equals(SeatStatus.AVAILABLE) ||
                    showseat.getSeatStatus().equals(SeatStatus.LOCKED)) {
                throw new NoSeatsAvailable();
            }
        }
        List<ShowSeat> SaveSeatList = new ArrayList<>();
        for (ShowSeat showseat : showSeats) {
            // lock the selected seat
            showseat.setSeatStatus(SeatStatus.LOCKED);
            showseat.setSeatLockeTime(new Date());
//            update the record
            SaveSeatList.add(showSeatRepository.save(showseat));

        }

        return showSeats;
    }
}
