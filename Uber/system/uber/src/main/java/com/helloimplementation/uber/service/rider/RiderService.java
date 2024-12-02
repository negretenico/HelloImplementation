package com.helloimplementation.uber.service.rider;

import com.helloimplementation.uber.model.database.Ride;
import com.helloimplementation.uber.model.database.Rider;
import com.helloimplementation.uber.model.util.Result;
import com.helloimplementation.uber.repository.RiderRepository;
import org.apache.coyote.Response;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class RiderService {
    RiderRepository riderRepository;
    public RiderService(RiderRepository riderRepository){
        this.riderRepository=riderRepository;
    }
    public Result<Rider> getRiderById(int id){
        Optional<Rider> possibleRider = riderRepository.findById(id);
        if(possibleRider.isEmpty()){
            return Result.failure(String.format("Could not find rider with id=%s",id));
        }
        return Result.success(possibleRider.get());
    }
}
