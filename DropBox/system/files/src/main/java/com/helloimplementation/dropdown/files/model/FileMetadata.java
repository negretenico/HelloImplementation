package com.helloimplementation.dropdown.files.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "filemetadata")
@Data
public class FileMetadata {
    @Id
    @Column(name = "file_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    @JoinColumn(name = "uploadedBy", referencedColumnName = "user_id")
    User user;
    String status;
    String s3Url;
    String name;
    String extension;
    @Column(name="file_size")
    int fileSize;
}
