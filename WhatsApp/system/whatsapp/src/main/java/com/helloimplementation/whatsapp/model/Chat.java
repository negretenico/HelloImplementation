package com.helloimplementation.whatsapp.model;

import lombok.Data;

import java.util.List;

@Data
public class Chat {
    int id;
    String name;
    List<User> particpants;
}
