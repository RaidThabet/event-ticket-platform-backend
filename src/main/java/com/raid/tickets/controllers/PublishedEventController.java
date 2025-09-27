package com.raid.tickets.controllers;

import com.raid.tickets.domain.dtos.GetPublishedEventDetailsResponseDto;
import com.raid.tickets.domain.dtos.ListPublishedEventResponseDto;
import com.raid.tickets.domain.entities.Event;
import com.raid.tickets.mappers.EventMapper;
import com.raid.tickets.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/published-events")
@RequiredArgsConstructor
public class PublishedEventController {

    private final EventService eventService;
    private final EventMapper eventMapper;

    @GetMapping
    public ResponseEntity<Page<ListPublishedEventResponseDto>> getPublishedEvents(
            Pageable pageable,
            @RequestParam(required = false) String q
    ) {
        Page<Event> events;
        if (q != null && !q.trim().isEmpty()) {
            events = eventService.searchPublishedEvents(q, pageable);
        } else {
            events = eventService.listPublishedEvents(pageable);
        }
        return ResponseEntity.ok(
                events.map(eventMapper::toListPublishedEventResponseDto)
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<GetPublishedEventDetailsResponseDto> getPublishedEvent(
            @PathVariable("id") UUID eventId
    ) {
        return eventService.getPublishedEvent(eventId)
                .map(eventMapper::toGetPublishedEventDetailsResponseDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}
