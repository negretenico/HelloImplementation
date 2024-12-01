package com.helloimplementation.uber.controller;

import com.helloimplementation.uber.model.database.Fare;
import com.helloimplementation.uber.model.database.Ride;
import com.helloimplementation.uber.model.requests.FareRequest;
import com.helloimplementation.uber.model.requests.RideRequest;
import com.helloimplementation.uber.model.util.Result;
import com.helloimplementation.uber.service.ride.RideService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/ride")
@RestController
public class RideController {
    RideService rideService;
    public RideController(RideService rideService){
        this.rideService=rideService;
    }
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Ride> createRide(@RequestBody RideRequest rideRequest){
        Result<Ride> rideResult  = rideService.save(rideRequest);
        return ResponseEntity.ok(rideResult.getData());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Ride> getFareById(@PathVariable int id){
        Result<Ride> rideResult = rideService.getById(id);
        if(rideResult.isFailure()){
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(rideResult.getData());
    }
}
