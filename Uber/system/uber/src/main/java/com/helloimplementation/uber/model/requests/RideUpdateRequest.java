package com.helloimplementation.uber.model.requests;

import com.helloimplementation.uber.model.util.RideStatus;
import lombok.Data;

@Data
public class RideUpdateRequest {
    RideStatus status;
}
