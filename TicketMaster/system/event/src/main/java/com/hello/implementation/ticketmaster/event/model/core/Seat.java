package com.hello.implementation.ticketmaster.event.model.core;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "seats")
public class Seat {
    @Id
    @Column(name = "seat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    @JoinColumn(name = "venue_id", referencedColumnName = "venue_id")
    Venue venue;
    int rowNumber;
    int seatNumber;
    String seatType;
}
