package com.hello.implementation.ticketmaster.event.model.core;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "performers")
@NoArgsConstructor
@Data
public class Performer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int performer_id;
    String name;
}