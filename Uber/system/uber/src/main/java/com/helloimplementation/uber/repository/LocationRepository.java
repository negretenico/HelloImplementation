package com.helloimplementation.uber.repository;

import com.helloimplementation.uber.model.database.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Integer> {
}
