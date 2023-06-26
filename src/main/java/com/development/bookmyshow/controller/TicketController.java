package com.development.bookmyshow.controller;

import com.development.bookmyshow.dto.BookTicketRequestDto;
import com.development.bookmyshow.dto.BookTicketResponseDto;
import com.development.bookmyshow.exception.InvalidArgumentException;
import com.development.bookmyshow.exception.NoSeatsAvailable;
import com.development.bookmyshow.model.Ticket;
import com.development.bookmyshow.service.TicketService;
import com.development.bookmyshow.service.impl.TicketServiceImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.concurrent.TimeoutException;

// in  controller in dont write any business logic. controller works is get the request and give the response
// and it will do some small validation like if id should not be blank from front end etc.

@Controller
// @Controller and @Service annotation tell to the spring to sort the order in topological format.

public class TicketController {


    private final TicketService ticketService;

    @Autowired
//    this annotation tell to spring to create the object of classes  and pass that object .
    public TicketController(TicketService ticketService) {

        this.ticketService = ticketService;
    }

    public BookTicketResponseDto bookTicket(BookTicketRequestDto bookTicketRequestDto) throws InvalidArgumentException, NoSeatsAvailable {

//         call service layer
        BookTicketResponseDto bookTicketResponseDto = new BookTicketResponseDto();
        try {
            Ticket ticket = ticketService.bookTicket(bookTicketRequestDto.getSeatId(),
                    bookTicketRequestDto.getShowId(),
                    bookTicketRequestDto.getUserId());


            bookTicketResponseDto.setTicketid(ticket.getId());
            bookTicketResponseDto.setAmount(ticket.getAmount());
            bookTicketResponseDto.setSeatNumber(ticket.getSeatList().toString());
            bookTicketResponseDto.setPaymentLink("payment link ");
        } catch (InvalidArgumentException | NoSeatsAvailable e) {
            throw new RuntimeException(e);
        }

        return bookTicketResponseDto;
    }

}
