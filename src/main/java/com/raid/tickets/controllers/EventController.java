package com.raid.tickets.controllers;

import com.raid.tickets.domain.dtos.CreateEventRequestDto;
import com.raid.tickets.domain.dtos.CreateEventResponseDto;
import com.raid.tickets.domain.entities.Event;
import com.raid.tickets.domain.request.CreateEventRequest;
import com.raid.tickets.mappers.EventMapper;
import com.raid.tickets.services.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventMapper eventMapper;
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<CreateEventResponseDto> createEvent(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateEventRequestDto request
    ) {
        UUID organizerId = UUID.fromString(jwt.getSubject());
        CreateEventRequest createEventRequest = eventMapper.fromDto(request);
        Event createdEvent = eventService.createEvent(organizerId, createEventRequest);

        CreateEventResponseDto responseDto = eventMapper.toDto(createdEvent);

        // TODO: sanitize user string input (prevent XSS)
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
