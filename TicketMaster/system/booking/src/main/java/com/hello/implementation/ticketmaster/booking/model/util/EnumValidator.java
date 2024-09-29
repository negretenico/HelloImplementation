package com.hello.implementation.ticketmaster.booking.model.util;

import java.util.Arrays;

public class EnumValidator {
    public static boolean isValidBookingStatus(String value){
        return Arrays.stream(Status.values()).anyMatch(s->s.name().equalsIgnoreCase(value));
    }
}
