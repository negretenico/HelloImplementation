package com.hello.implementation.ticketmaster.booking.controller;

import com.hello.implementation.ticketmaster.booking.model.Ticket;
import com.hello.implementation.ticketmaster.booking.model.util.EnumValidator;
import com.hello.implementation.ticketmaster.booking.model.util.ResultStatus;
import com.hello.implementation.ticketmaster.booking.model.util.Status;
import com.hello.implementation.ticketmaster.booking.service.TicketService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/ticket")
@RestController
public class TicketController {
    TicketService ticketService;
    public TicketController(TicketService ticketService){
        this.ticketService=ticketService;
    }
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Ticket>> getAllTickets(){
        return ResponseEntity.ok(ticketService.getAll());
    }
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Ticket> save(@RequestBody Ticket ticket) {
        return ResponseEntity.ok(ticketService.save(ticket));
    }
    @PatchMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResultStatus> updateBooking(@PathVariable int id, @RequestBody Map<String,String> body) {
        String status = body.getOrDefault("status","");
        if(!EnumValidator.isValidBookingStatus(status)){
            return ResponseEntity.badRequest().build();
        }
        try {
            return ResponseEntity.ok(ticketService.updateTicket(id, Status.valueOf(status)));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultStatus> reserve(@PathVariable int id, @RequestBody Map<String,String> body) {
        try {
            return ResponseEntity.ok(ticketService.updateTicket(id, Status.PENDED));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResultStatus> confirm(@PathVariable int id, @RequestBody Map<String, Object> body) {
        try {
            return ResponseEntity.ok(ticketService.updateTicket(id, Status.PENDED));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
