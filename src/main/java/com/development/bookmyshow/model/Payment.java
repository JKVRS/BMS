package com.development.bookmyshow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Payment extends BaseModel {

    @Enumerated(EnumType.ORDINAL)
    private PaymentProvider paymentProvider;
    private Date date;
    private String refId;

    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus paymentStatus;
    @Enumerated(EnumType.ORDINAL)
    private PaymentType paymentType;

    @ManyToOne
    private Ticket ticket;
    private int amount;

}
