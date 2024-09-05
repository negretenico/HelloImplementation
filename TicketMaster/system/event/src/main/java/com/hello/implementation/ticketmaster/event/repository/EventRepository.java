package com.hello.implementation.ticketmaster.event.repository;

import com.hello.implementation.ticketmaster.event.model.core.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
}
