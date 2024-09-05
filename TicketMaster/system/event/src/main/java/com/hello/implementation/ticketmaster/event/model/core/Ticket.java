//package com.hello.implementation.ticketmaster.event.model.core;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//@Data
//@Entity
//@Table(name = "Tickets")
//public class Ticket {
//    @Id
//    @Column(name = "ticket_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    int id;
//
//    @ManyToOne
//    @JoinColumn(name = "seat_id", referencedColumnName = "seat_id")
//    Seat seat;
//    @ManyToOne
//    @JoinColumn(name = "event_id", referencedColumnName = "event_id")
//    Event event;
//    String status;
//    String buyerName;
//    double price;
//}
