package com.raid.tickets.services;

import com.raid.tickets.domain.entities.Event;
import com.raid.tickets.domain.request.CreateEventRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface EventService {
    Event createEvent(UUID organizerId, CreateEventRequest event);
    Page<Event> listEventsForOrganizer(UUID organizeId, Pageable pageable);
}
