package com.helloimplementation.uber.model.requests;

import lombok.Data;

@Data
public class PartialRide {
    int fareId;
    int pickupId;
    int dropoffId;
}
