package com.helloimplementation.whatsapp.model;

import lombok.Data;

import java.util.List;
@Data
public class Message {
    int id;
    int chatId;
    String message;
    List<Attachment> attachments;
}
