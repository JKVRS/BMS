package com.development.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookTicketResponseDto {
    private long ticketid;
    private String paymentLink;
    private String SeatNumber;
    private double amount;
}
