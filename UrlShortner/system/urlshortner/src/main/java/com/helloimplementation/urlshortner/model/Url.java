package com.helloimplementation.urlshortner.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "url")
@Data
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generate a primary key
    @Column(name = "id")
    private Long id;  // Primary key for the entity

    @Column(name = "original_url", nullable = false, unique = true)
    private String originalUrl;  // Original URL

    @Column(name = "shorten_url", nullable = false, unique = true)
    private String shortenedUrl;  // Shortened URL
}
