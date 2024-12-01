package com.helloimplementation.uber.model.database;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "fare")
@Data
public class Fare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fare_id")
    private int id;
    @Column(name="price_estimate", nullable = false)
    float priceEstimate;
    @Column(name="time_estimate")
    int timeEstimate;
    @Column(name="distance_estimate")
    int distanceEstimate;
}
