package com.development.bookmyshow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Seats")
public class Seat extends BaseModel {

    private String seatNumber;
    @Column(name = "rowz")
    private int row;
    @Column(name = "colz")
    private int col;
    private int totalNumberSeat;

    @Enumerated(EnumType.ORDINAL)
    private SeatStatus seatStatus;

    @ManyToOne
    private SeatType seatType;
}
