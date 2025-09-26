package com.raid.tickets.services.impl;

import com.raid.tickets.domain.entities.Event;
import com.raid.tickets.domain.entities.TicketType;
import com.raid.tickets.domain.entities.User;
import com.raid.tickets.domain.request.CreateEventRequest;
import com.raid.tickets.domain.request.UpdateEventRequest;
import com.raid.tickets.domain.request.UpdateTicketTypeRequest;
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

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @Override
    public Optional<Event> getEventForOrganizer(UUID organizerId, UUID id) {
        return eventRepository.findByIdAndOrganizerId(id, organizerId);
    }

    @Override
    @Transactional
    public Event updateEventForOrganizer(UUID organizerId, UUID id, UpdateEventRequest eventRequest) {
        if (eventRequest.getId() == null) {
            throw new BusinessException(ErrorCode.EVENT_NOT_FOUND, id);
        }

        if (!id.equals(eventRequest.getId())) {
            throw new BusinessException(ErrorCode.EVENT_ID_UPDATE);
        }

        Event event = eventRepository
                .findByIdAndOrganizerId(id, organizerId)
                .orElseThrow(() -> new BusinessException(ErrorCode.EVENT_NOT_FOUND, id));

        event.setName(eventRequest.getName());
        event.setStartDateTime(eventRequest.getStartDateTime());
        event.setEndDateTime(eventRequest.getEndDateTime());
        event.setVenue(eventRequest.getVenue());
        event.setSalesStart(eventRequest.getSalesStart());
        event.setSalesEnd(eventRequest.getSalesEnd());
        event.setStatus(eventRequest.getStatus());

        Set<UUID> requestTicketTypesIds = eventRequest.getTicketTypes().stream()
                .map(UpdateTicketTypeRequest::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        event.getTicketTypes().removeIf(
                existingTicketType -> !requestTicketTypesIds.contains(existingTicketType.getId())
        );

        Map<UUID, TicketType> existingTicketTypesIndex = event.getTicketTypes().stream()
                .collect(Collectors.toMap(TicketType::getId, Function.identity()));

        for (UpdateTicketTypeRequest ticketType: eventRequest.getTicketTypes()) {
            if (null == ticketType.getId()) {
                // Create case
                TicketType ticketTypeToCreate = new TicketType();
                ticketTypeToCreate.setName(ticketType.getName());
                ticketTypeToCreate.setPrice(ticketType.getPrice());
                ticketTypeToCreate.setDescription(ticketType.getDescription());
                ticketTypeToCreate.setTotalAvailable(ticketType.getTotalAvailable());
                ticketTypeToCreate.setEvent(event);
                event.getTicketTypes().add(ticketTypeToCreate);
            } else if (existingTicketTypesIndex.containsKey(ticketType.getId())) {
                // Update case
                TicketType existingTicketType = existingTicketTypesIndex.get(ticketType.getId());
                existingTicketType.setName(ticketType.getName());
                existingTicketType.setPrice(ticketType.getPrice());
                existingTicketType.setDescription(ticketType.getDescription());
                existingTicketType.setTotalAvailable(ticketType.getTotalAvailable());
            } else {
                throw new BusinessException(ErrorCode.TICKET_TYPE_NOT_FOUND, ticketType.getId());
            }
        }

        return eventRepository.save(event);
    }

    @Override
    @Transactional
    public void deleteEventForOrganizer(UUID organizeId, UUID id) {
        getEventForOrganizer(organizeId, id).ifPresent(eventRepository::delete);
    }
}
