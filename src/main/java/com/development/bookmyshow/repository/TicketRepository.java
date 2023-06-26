package com.development.bookmyshow.repository;

import com.development.bookmyshow.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {


    public Ticket save(Ticket ticket);
}
