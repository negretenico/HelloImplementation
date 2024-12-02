package com.helloimplementation.uber.controller;

import com.helloimplementation.uber.model.database.Driver;
import com.helloimplementation.uber.model.database.Location;
import com.helloimplementation.uber.model.util.Result;
import com.helloimplementation.uber.service.driver.DriverService;
import com.helloimplementation.uber.service.location.LocationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.function.Supplier;

@RestController
@RequestMapping(value = "/api/drivers")
public class DriverController {
    DriverService driverService;
    LocationService locationService;
    public DriverController(DriverService driverService,LocationService locationService){
        this.driverService=driverService;
        this.locationService=locationService;
    }
    @PostMapping(value = "/location",consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getLocation(@RequestBody Location location,  @CookieValue(value = "SESSION_ID", defaultValue = "0") String id){
        Optional<ResponseEntity<String>> driver = createResult(()->driverService.findById(id));
        Optional<ResponseEntity<String>> locationResult = createResult(()->locationService.findByLatAndLong(location.getLatitude(),location.getLongitude()));
        return locationResult.orElseGet(() -> driver.orElseGet(() -> ResponseEntity.ok("SUCCESS")));
    }
    private <T> Optional<ResponseEntity<String>> createResult(Supplier<Result<T>> supplier){
        Result<T> result = supplier.get();
        if(!result.isFailure()){
            return Optional.empty();
        }
        return Optional.of( ResponseEntity.unprocessableEntity().body(result.getErrorMsg()));
    }
}
