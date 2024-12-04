package com.helloimplementation.youtube.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    long userId;
    String name;
    String email;
}
