package com.helloimplementation.uber.model.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RideRequest {
    int riderId;
    int fareId;
    int pickupId;
    int dropoffId;
}
