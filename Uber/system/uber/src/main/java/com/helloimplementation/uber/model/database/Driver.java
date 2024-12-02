package com.helloimplementation.uber.model.database;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "driver")
@Data
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private int id;
    @Column(name="name", nullable = false)
    String name;
}
