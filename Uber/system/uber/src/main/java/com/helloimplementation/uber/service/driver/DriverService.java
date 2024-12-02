package com.helloimplementation.uber.service.driver;

import com.helloimplementation.uber.model.database.Driver;
import com.helloimplementation.uber.model.util.Result;
import com.helloimplementation.uber.repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DriverService {
    DriverRepository driverRepository;
    public DriverService(DriverRepository driverRepository){
        this.driverRepository=driverRepository;
    }
    public Result<Driver> findById(String id){
        int localId;
        try{
            localId= Integer.parseInt(id);
        }catch (NumberFormatException e){
            return Result.failure(String.format("We could not create a number from %s",id));
        }
        Optional<Driver> possibleDriver = driverRepository.findById(localId);
        if(possibleDriver.isEmpty()){
            return  Result.failure(String.format("We could not find a driver with id=%s",localId));
        }
        return Result.success(possibleDriver.get());
    }
}
