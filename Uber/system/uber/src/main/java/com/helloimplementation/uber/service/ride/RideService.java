package com.helloimplementation.uber.service.ride;

import com.helloimplementation.uber.model.database.*;
import com.helloimplementation.uber.model.requests.RideRequest;
import com.helloimplementation.uber.model.util.Result;
import com.helloimplementation.uber.model.util.RideStatus;
import com.helloimplementation.uber.repository.RideRepository;
import com.helloimplementation.uber.service.fare.FareService;
import com.helloimplementation.uber.service.location.LocationService;
import com.helloimplementation.uber.service.ride.matching.DriverMatchingService;
import com.helloimplementation.uber.service.rider.RiderService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;
@Service
public class RideService {
    FareService fareService;
    DriverMatchingService driverMatchingService;
    RiderService riderService;
    LocationService locationService;
    RideRepository rideRepository;
    public RideService(LocationService locationService,FareService fareService,DriverMatchingService driverMatchingService,RiderService riderService,RideRepository rideRepository){
        this.fareService=fareService;
        this.locationService=locationService;
        this.riderService=riderService;
        this.rideRepository=rideRepository;
        this.driverMatchingService=driverMatchingService;
    }
    public Result<Ride> save(RideRequest rideRequest) {
        // Validate and fetch related entities
        Result<Fare> fareResult = fetchValidatedEntity(
                () -> fareService.getById(rideRequest.getFareId()),
                "Invalid fare ID"
        );
        if (fareResult.isFailure()) return Result.failure(fareResult.getErrorMsg());

        Result<Rider> riderResult = fetchValidatedEntity(
                () -> riderService.getRiderById(rideRequest.getRiderId()),
                "Invalid rider ID"
        );
        if (riderResult.isFailure()) return Result.failure(riderResult.getErrorMsg());

        Result<Location> pickupResult = fetchValidatedEntity(
                () -> locationService.getById(rideRequest.getPickupId()),
                "Invalid pickup location ID"
        );
        if (pickupResult.isFailure()) return Result.failure(pickupResult.getErrorMsg());

        Result<Location> dropoffResult = fetchValidatedEntity(
                () -> locationService.getById(rideRequest.getDropoffId()),
                "Invalid dropoff location ID"
        );
        if (dropoffResult.isFailure()) return Result.failure(dropoffResult.getErrorMsg());

        // Match driver
        Driver matchedDriver = driverMatchingService.getDriver();
        if (matchedDriver == null) {
            return Result.failure("No drivers available");
        }

        // Assemble and save the ride
        Ride ride = new Ride();
        ride.setFare(fareResult.getData());
        ride.setRider(riderResult.getData());
        ride.setPickupLocation(pickupResult.getData());
        ride.setDropoffLocation(dropoffResult.getData());
        ride.setDriver(matchedDriver);
        ride.setStatus(RideStatus.STARTED.name());
        return Result.success( rideRepository.save(ride));
    }

    // Helper method for validation and entity retrieval
    private <T> Result<T> fetchValidatedEntity(Supplier<Result<T>> supplier, String errorMsg) {
        Result<T> result = supplier.get();
        if (result.isFailure()) {
            return Result.failure(errorMsg + ": " + result.getErrorMsg());
        }
        return result;
    }

    public Result<Ride> getById(int id){
        Optional<Ride> possibleRide = rideRepository.findById(id);
        if(possibleRide.isEmpty()){
            return Result.failure(String.format("We could not find the ride for id %s",id));
        }
        return Result.success(possibleRide.get());
    }
}
