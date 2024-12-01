package com.helloimplementation.uber.repository;

import com.helloimplementation.uber.model.database.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Integer> {
}
