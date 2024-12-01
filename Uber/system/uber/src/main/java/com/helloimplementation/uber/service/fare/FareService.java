package com.helloimplementation.uber.service.fare;

import com.helloimplementation.uber.model.database.Fare;
import com.helloimplementation.uber.model.database.Location;
import com.helloimplementation.uber.model.util.Result;
import com.helloimplementation.uber.repository.FareRepository;
import com.helloimplementation.uber.service.fare.distance.DistanceService;
import com.helloimplementation.uber.service.fare.price.PriceService;
import com.helloimplementation.uber.service.fare.time.TimeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FareService {
    FareRepository fareRepository;
    public FareService(FareRepository fareRepository) {
        this.fareRepository = fareRepository;
    }
    public Result<Fare>save(Location pickup, Location dropoff){
        double distanceEstimate =DistanceService.getDistance(pickup,dropoff);
        float priceEstimate = PriceService.getPriceForDistance(distanceEstimate);
        int timeEstimate = TimeService.getTimeEstimate(distanceEstimate);
        Fare fare = new Fare();
        fare.setDistanceEstimate((int)distanceEstimate);
        fare.setPriceEstimate(priceEstimate);
        fare.setTimeEstimate(timeEstimate);
        Fare savedFare = fareRepository.save(fare);
        return Result.success(savedFare);
    }
    public Result<Fare> getById(int id){
        Optional<Fare> possibleFare = fareRepository.findById(id);
        if(possibleFare.isEmpty()){
            return Result.failure(String.format("Could not find fare with id %s",id));
        }
        return Result.success(possibleFare.get());
    }
}
