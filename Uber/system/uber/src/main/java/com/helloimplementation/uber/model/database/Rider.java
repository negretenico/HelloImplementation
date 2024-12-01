package com.helloimplementation.uber.model.database;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rider")
@Data
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rider_id")
    private int id;
    @Column(name="name", nullable = false)
    String name;
}
