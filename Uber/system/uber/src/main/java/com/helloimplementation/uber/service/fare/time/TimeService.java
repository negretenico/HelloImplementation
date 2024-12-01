package com.helloimplementation.uber.service.fare.time;

import org.springframework.stereotype.Service;

public class TimeService {
    public static int getTimeEstimate(double distance){
        double BASE_LINE = 10_000F;
        int CLOSE_DISTANCE = 5; // these times are in minutes
        int LONG_DISTANCE = 20;
        if( Double.compare(distance, BASE_LINE)>=0){
            return LONG_DISTANCE;
        }
        return CLOSE_DISTANCE;
    }
}
