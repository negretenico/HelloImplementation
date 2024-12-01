package com.helloimplementation.uber.model.database;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "location")
@Data
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    int id;
    @Column(name = "latitude")
    float latitude;
    @Column(name = "longitude")
    float longitude;
    @Column(name="name")
    String name;
}
