package com.helloimplementation.youtube.models;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class User implements Serializable {
    UUID userId;
    String name;
    String email;
}
