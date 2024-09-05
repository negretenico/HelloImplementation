package com.hello.implementation.ticketmaster.event.controller;

import com.hello.implementation.ticketmaster.event.model.core.Event;
import com.hello.implementation.ticketmaster.event.model.util.DeleteStatus;
import com.hello.implementation.ticketmaster.event.service.EventService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/event")
public class EventController {
    EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Event> save(@RequestBody Event event) {
        return ResponseEntity.ok(eventService.save(event));
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Event>> getAll() {
        return ResponseEntity.ok(eventService.getAll());
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Event> getById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(eventService.getEvent(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Event> updateEvent(@PathVariable int id, @RequestBody Map<String, Object> updates) {
        try {
            return ResponseEntity.ok(eventService.update(id, updates));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<DeleteStatus> deleteEvent(@PathVariable int id) {
        return ResponseEntity.ok(eventService.delete(id));
    }
}
