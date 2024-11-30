package com.helloimplementation.whatsapp.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class OutboundMesssage {
    Message message;
    Timestamp timestamp;
}
