package com.development.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookTicketRequestDto {

    private List<Long> seatId;
    private long showId;
    private long userId;

}
