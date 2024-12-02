package com.helloimplementation.uber.repository;

import com.helloimplementation.uber.model.database.Ride;
import com.helloimplementation.uber.model.database.Rider;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiderRepository extends JpaRepository<Rider, Integer> {
}
