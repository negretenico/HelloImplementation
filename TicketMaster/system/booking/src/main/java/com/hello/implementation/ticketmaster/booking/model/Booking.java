package com.hello.implementation.ticketmaster.booking.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @Column(name = "booking_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int user_id;
    @ManyToOne // one booking can have multiple tickets
    @JoinColumn(name = "ticket_id", referencedColumnName = "ticket_id")
    Ticket ticket;
}
