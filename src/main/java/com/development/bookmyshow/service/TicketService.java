package com.development.bookmyshow.service;

import com.development.bookmyshow.exception.InvalidArgumentException;
import com.development.bookmyshow.exception.NoSeatsAvailable;
import com.development.bookmyshow.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {


    public Ticket bookTicket(List<Long> seatId, long showId, long userId) throws InvalidArgumentException, NoSeatsAvailable;
}
