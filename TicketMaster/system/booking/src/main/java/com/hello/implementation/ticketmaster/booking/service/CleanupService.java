package com.hello.implementation.ticketmaster.booking.service;

import com.hello.implementation.ticketmaster.booking.model.Ticket;
import com.hello.implementation.ticketmaster.booking.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CleanupService {
    private static final Logger log = LoggerFactory.getLogger(CleanupService.class);
    TicketRepository ticketRepository;
    Long ttl;
    public CleanupService(TicketRepository ticketRepository, @Value("${tickets.ttl}")Long ttl){
        this.ticketRepository=ticketRepository;
        this.ttl=ttl;
    }
    @Scheduled(fixedRate = 60000)
    public void deleteExpiredTickets(){
        LocalDateTime expireDate = LocalDateTime.now().minusMinutes(ttl);
        List<Ticket> tickets = ticketRepository.findTicketsToDelete(expireDate);
        if(tickets.isEmpty()){
            log.info("We do not have any tickets to clean up");
            return;
        }
        log.info("We are updating {}",tickets);
        ticketRepository.deleteAll(tickets);
    }
}
