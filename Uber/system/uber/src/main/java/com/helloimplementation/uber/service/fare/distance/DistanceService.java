package com.helloimplementation.uber.service.fare.distance;

import com.helloimplementation.uber.model.database.Location;

public class DistanceService {
    public static double getDistance(Location pickup, Location destination) {
        // Earth's radius in kilometers
        final double EARTH_RADIUS_KM = 6371.0;

        // Ensure input validity
        if (pickup == null || destination == null) {
            throw new IllegalArgumentException("Pickup and destination locations must not be null.");
        }

        // Convert latitudes and longitudes to radians
        double pickupLatitude = Math.toRadians(pickup.getLatitude());
        double c = calculateHaversineFormula(pickup, destination, pickupLatitude);

        // Return distance in meters
        return EARTH_RADIUS_KM * c * 1000;
    }

    private static double calculateHaversineFormula(Location pickup, Location destination, double pickupLatitude) {
        double pickupLongitude = Math.toRadians(pickup.getLongitude());
        double destinationLatitude = Math.toRadians(destination.getLatitude());
        double destinationLongitude = Math.toRadians(destination.getLongitude());

        // Calculate latitude and longitude differences
        double latDistance = destinationLatitude - pickupLatitude;
        double longDistance = destinationLongitude - pickupLongitude;

        // Haversine formula
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(pickupLatitude) * Math.cos(destinationLatitude)
                * Math.sin(longDistance / 2) * Math.sin(longDistance / 2);
        return  2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    }

}
