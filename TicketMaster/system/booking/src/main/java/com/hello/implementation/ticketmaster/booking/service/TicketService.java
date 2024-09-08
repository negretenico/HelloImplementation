package com.hello.implementation.ticketmaster.booking.service;

import com.hello.implementation.ticketmaster.booking.model.Ticket;
import com.hello.implementation.ticketmaster.booking.model.util.ResultStatus;
import com.hello.implementation.ticketmaster.booking.model.util.Status;
import com.hello.implementation.ticketmaster.booking.repository.TicketRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private static final Logger log = LoggerFactory.getLogger(TicketService.class);
    TicketRepository ticketRepository;
    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository=ticketRepository;
    }

    public Ticket save(Ticket ticket){
        log.info("Saving ticket with id={}",ticket.getId());
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAll(){
        log.info("Retrieving all tickets");
        return ticketRepository.findAll();
    }
    public Ticket getTicket(int id) throws  EntityNotFoundException{
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if(ticket.isEmpty()){
            log.info("Could not find ticket with id={}",id);
            throw new EntityNotFoundException(String.format("Could not find this id %s",id));
        }
        return ticket.get();
    }

    public ResultStatus updateTicket(int id, Status status) {
        Ticket ticket;
        try{
            ticket = getTicket(id);
        }catch (EntityNotFoundException  e){
            log.info("Could not find ticket with id={},could not update status",id);
            return ResultStatus.FAILURE;
        }
        if(status.equals(Status.PENDED) && !ticket.getStatus().equals(Status.PENDED.name())){
            log.info("Ticket is moving to `PENDED` status updating booking time");
            ticket.setBookedAt(LocalDateTime.now());
        }
        ticket.setStatus(status.name());
        save(ticket);
        return ResultStatus.SUCCESS;
    }
}
