package com.helloimplementation.uber.service.location;

import com.helloimplementation.uber.model.database.Location;
import com.helloimplementation.uber.model.util.Result;
import com.helloimplementation.uber.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@Service
public class LocationService {
    LocationRepository locationRepository;
    public LocationService(LocationRepository locationRepository){
        this.locationRepository=locationRepository;
    }
    public Result<Location> getById(int id){
        return createResult(()->locationRepository.findById(id),String.format("We could not find the location with id=%s",id));
    }
    public Result<Location> findByLatAndLong(float lat, float longitude){
        return createResult(()->locationRepository.findLocationByLatAndLong(lat,longitude),String.format("We could not find the location with lat=%s and long=%s",lat,longitude));
    }
    private Result<Location> createResult(Supplier<Optional<Location>> supplier, String errorMsg) {
        Optional<Location> result = supplier.get();
        if (result.isEmpty()) {
            return Result.failure(errorMsg );
        }
        return Result.success(result.get());
    }

}
