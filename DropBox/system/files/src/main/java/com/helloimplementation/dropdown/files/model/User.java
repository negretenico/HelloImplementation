package com.helloimplementation.dropdown.files.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    int id;
    @Column(name = "name")
    String name;
    // Bi-directional relationship (optional, depending on your use case)
    @OneToMany(mappedBy = "user")
    private List<FileMetadata> filesUploaded;

}
