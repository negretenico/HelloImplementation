package com.helloimplementation.dropdown.files.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "fileshares")
@Data
public class FileShares {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "file_id", referencedColumnName = "file_id", nullable = false)
    private FileMetadata fileMetadata;

    @Column(name = "shared_on", nullable = false)
    private Timestamp sharedOn;
}
