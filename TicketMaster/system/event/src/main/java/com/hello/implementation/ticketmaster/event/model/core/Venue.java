package com.hello.implementation.ticketmaster.event.model.core;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "venues")
public class Venue {
    @Id
    @Column(name = "venue_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int venueId;
    String name;
    String location;
}
