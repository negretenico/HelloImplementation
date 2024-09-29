package com.hello.implementation.ticketmaster.booking.model;

import com.hello.implementation.ticketmaster.booking.model.Event;
import com.hello.implementation.ticketmaster.booking.model.Seat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "Tickets")
@Data
public class Ticket {
    @Id
    @Column(name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    @JoinColumn(name = "seat_id", referencedColumnName = "seat_id")
    Seat seat;
    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "event_id")
    Event event;
    @Column(name="booked_at")
    LocalDateTime bookedAt;
    String status;
    String buyerName;
    double price;
}
