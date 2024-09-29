package com.hello.implementation.ticketmaster.event.model.core;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "seatmaps")
public class SeatMap {
    @Id
    @Column(name = "seat_map_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    @JoinColumn(name = "venue_id", referencedColumnName = "venue_id")
    Venue venue;
    String layout;
}
