package com.helloimplementation.uber.service.location;

import com.helloimplementation.uber.model.database.Location;
import com.helloimplementation.uber.model.util.Result;
import com.helloimplementation.uber.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationService {
    LocationRepository locationRepository;
    public LocationService(LocationRepository locationRepository){
        this.locationRepository=locationRepository;
    }
    public Result<Location> getById(int id){
        Optional<Location> possibleLocation = locationRepository.findById(id);
        if(possibleLocation.isEmpty()){
            return Result.failure(String.format("We could not find the location with id=%s",id));
        }
        return Result.success(possibleLocation.get());
    }
}
