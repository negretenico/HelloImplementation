package com.helloimplementation.uber.model.database;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "Ride")
@Data
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ride_id")
    private Integer rideId;

    @ManyToOne
    @JoinColumn(name = "fare_id", referencedColumnName = "fare_id", nullable = false)
    private Fare fare;

    @ManyToOne
    @JoinColumn(name = "pickup_location", referencedColumnName = "location_id", nullable = false)
    private Location pickupLocation;

    @ManyToOne
    @JoinColumn(name = "dropoff_location", referencedColumnName = "location_id", nullable = false)
    private Location dropoffLocation;

    @ManyToOne
    @JoinColumn(name = "rider_id", referencedColumnName = "rider_id", nullable = false)
    private Rider rider;

    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "driver_id", nullable = false)
    private Driver driver;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "requested_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime requestedAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;
}
