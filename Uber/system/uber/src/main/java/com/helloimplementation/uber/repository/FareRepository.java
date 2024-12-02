package com.helloimplementation.uber.repository;

import com.helloimplementation.uber.model.database.Fare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FareRepository  extends JpaRepository<Fare,Integer> {
}
