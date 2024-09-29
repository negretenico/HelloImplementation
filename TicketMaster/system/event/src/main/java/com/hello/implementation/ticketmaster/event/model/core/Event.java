package com.hello.implementation.ticketmaster.event.model.core;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "events")
public class Event {
    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String description;
    Date date;
    @ManyToOne
    @JoinColumn(name = "venue_id", referencedColumnName = "venue_id")
    Venue venue;
    @ManyToOne
    @JoinColumn(name = "performer_id", referencedColumnName = "performer_id")
    Performer performer;
}
