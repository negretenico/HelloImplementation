package com.helloimplementation.uber.repository;

import com.helloimplementation.uber.model.database.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location,Integer> {
    @Query("SELECT l FROM Location l WHERE ABS(l.latitude - :latitude) < 0.0001 AND ABS(l.longitude - :longitude) < 0.0001")
    Optional<Location> findLocationByLatAndLong(@Param("latitude") float latitude, @Param("longitude") float longitude);
}
