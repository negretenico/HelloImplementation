package com.helloimplementation.uber.controller;

import com.helloimplementation.uber.model.database.Fare;
import com.helloimplementation.uber.model.requests.FareRequest;
import com.helloimplementation.uber.model.util.Result;
import com.helloimplementation.uber.service.fare.FareService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/fare")
@RestController
public class FareController {
    FareService fareService;
    public FareController(FareService fareService){
        this.fareService=fareService;
    }
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Fare> getPriceEstimate(@RequestBody FareRequest fareRequest){
        Result<Fare> fareResult = fareService.save(fareRequest.getPickup(),fareRequest.getDropoff());
        return ResponseEntity.ok(fareResult.getData());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Fare> getFareById(@PathVariable int id){
        Result<Fare> fareResult = fareService.getById(id);
        if(fareResult.isFailure()){
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(fareResult.getData());
    }
}
