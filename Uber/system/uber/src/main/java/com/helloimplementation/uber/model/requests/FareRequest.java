package com.helloimplementation.uber.model.requests;

import com.helloimplementation.uber.model.database.Location;
import lombok.Data;

@Data
public class FareRequest {
    Location pickup;
    Location dropoff;
}
