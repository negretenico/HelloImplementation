package com.hello.implementation.ticketmaster.booking.repository;

import com.hello.implementation.ticketmaster.booking.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    @Query("SELECT t FROM Ticket t WHERE t.status = 'PENDED' AND t.bookedAt < :expirationTime")
    List<Ticket> findTicketsToDelete(@Param("expirationTime") LocalDateTime expirationTime);
}
