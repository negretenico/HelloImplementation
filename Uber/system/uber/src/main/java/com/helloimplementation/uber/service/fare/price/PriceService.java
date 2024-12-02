package com.helloimplementation.uber.service.fare.price;

public class PriceService {
    public static float getPriceForDistance(double distance){
        double BASE_LINE = 10_000F;
        float CLOSE_DISTANCE = 20; // these are just estimates in USD
        float LONG_DISTANCE = 50;
        if( Double.compare(distance, BASE_LINE)>=0){
            return LONG_DISTANCE;
        }
        return CLOSE_DISTANCE;
    }
}
