package com.raid.tickets.services.impl;

import com.raid.tickets.domain.entities.Event;
import com.raid.tickets.domain.entities.TicketType;
import com.raid.tickets.domain.entities.User;
import com.raid.tickets.domain.request.CreateEventRequest;
import com.raid.tickets.exceptions.BusinessException;
import com.raid.tickets.exceptions.ErrorCode;
import com.raid.tickets.repositories.EventRepository;
import com.raid.tickets.repositories.UserRepository;
import com.raid.tickets.services.EventService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    @Override
    @Transactional
    public Event createEvent(UUID organizerId, CreateEventRequest event) {
        User organizer = userRepository.findById(organizerId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND, organizerId));

        Event eventToCreate = new Event();

        List<TicketType> ticketTypesToCreate = event.getTicketTypes().stream()
                .map(ticketType -> {
                    TicketType ticketTypeToCreate = new TicketType();
                    ticketTypeToCreate.setName(ticketType.getName());
                    ticketTypeToCreate.setPrice(ticketType.getPrice());
                    ticketTypeToCreate.setDescription(ticketType.getDescription());
                    ticketTypeToCreate.setTotalAvailable(ticketType.getTotalAvailable());
                    ticketTypeToCreate.setEvent(eventToCreate);

                    return ticketTypeToCreate;
                }).toList();

        eventToCreate.setName(event.getName());
        eventToCreate.setStartDateTime(event.getStartDateTime());
        eventToCreate.setEndDateTime(event.getEndDateTime());
        eventToCreate.setVenue(event.getVenue());
        eventToCreate.setSalesStart(event.getSalesStart());
        eventToCreate.setSalesEnd(event.getSalesEnd());
        eventToCreate.setStatus(event.getStatus());
        eventToCreate.setOrganizer(organizer);
        eventToCreate.setTicketTypes(ticketTypesToCreate);

        return eventRepository.save(eventToCreate);
    }

    @Override
    public Page<Event> listEventsForOrganizer(UUID organizeId, Pageable pageable) {
        return eventRepository.findByOrganizerId(organizeId, pageable);
    }
}
