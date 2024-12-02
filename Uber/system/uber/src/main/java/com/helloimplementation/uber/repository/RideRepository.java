package com.helloimplementation.uber.repository;

import com.helloimplementation.uber.model.database.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RideRepository extends JpaRepository<Ride, Integer> {
}
