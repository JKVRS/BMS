package com.development.bookmyshow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.prefs.BackingStoreException;

@Getter
@Setter
@Entity
public class Ticket extends BaseModel {
    @ManyToOne
    private User user;
    private Date ticketBookingDate;
    private double amount;
    @ManyToOne
    private Show show;
    @ManyToMany
    private List<Seat> seatList;
    @OneToMany(mappedBy = "ticket")
    private List<Payment> paymentList;

    @Enumerated(EnumType.ORDINAL)
    private TicketStatus ticketStatus;

}
