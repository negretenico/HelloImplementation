package com.helloimplementation.whatsapp.controller;

import com.helloimplementation.whatsapp.model.Message;
import com.helloimplementation.whatsapp.model.OutboundMesssage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
public class ChatController {
    @MessageMapping("/chat")
    @SendTo("/topic/message")
    public OutboundMesssage send(final Message message) {
        System.out.println("HELLo");
        return OutboundMesssage.builder().message(message).timestamp(Timestamp.valueOf(LocalDateTime.now())).build();
    }
}
