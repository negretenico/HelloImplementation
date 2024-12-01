package com.helloimplementation.uber.model.requests;

import lombok.Data;

@Data
public class RideRequest {
    int fareId;
    int riderId;
    int pickupId;
    int dropoffId;
}
