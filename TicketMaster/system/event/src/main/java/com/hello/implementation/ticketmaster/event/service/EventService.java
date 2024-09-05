package com.hello.implementation.ticketmaster.event.service;

import com.hello.implementation.ticketmaster.event.model.core.Event;
import com.hello.implementation.ticketmaster.event.model.util.DeleteStatus;
import com.hello.implementation.ticketmaster.event.repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EventService {
    private static final Logger log = LoggerFactory.getLogger(EventService.class);
    EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getAll() {
        return List.copyOf(eventRepository.findAll());
    }

    public Event getEvent(int id) throws EntityNotFoundException {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isEmpty()) {
            throw new EntityNotFoundException(String.format("This id %s is missing", id));
        }
        return event.get();
    }

    public Event update(int id, Map<String, Object> updates) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id " + id));
        updates.forEach((fieldName, value) -> {
            try {
                Field field = Event.class.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(event, value);
            } catch (NoSuchFieldException e) {
                log.error(String.format("This field is not present %s", fieldName));
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        return eventRepository.save(event);
    }

    public DeleteStatus delete(int id) {
        try {
            eventRepository.deleteById(id);
            return DeleteStatus.SUCCESS;
        } catch (EmptyResultDataAccessException e) {
            return DeleteStatus.FAILURE;
        }
    }
}