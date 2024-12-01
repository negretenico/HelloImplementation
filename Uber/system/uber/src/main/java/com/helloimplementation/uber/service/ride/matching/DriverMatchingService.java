package com.helloimplementation.uber.service.ride.matching;

import com.helloimplementation.uber.model.database.Driver;
import com.helloimplementation.uber.repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class DriverMatchingService {
    DriverRepository driverRepository;
    public DriverMatchingService(DriverRepository driverRepository){
        this.driverRepository=driverRepository;
    }
    public Driver getDriver(){
        List<Driver> drivers = driverRepository.findAll();
        Random rng = new Random();
        int next = rng.nextInt(drivers.size());
        return  drivers.get(next);
    }
}
