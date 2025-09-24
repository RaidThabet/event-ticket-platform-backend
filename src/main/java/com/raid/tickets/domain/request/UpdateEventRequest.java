package com.raid.tickets.domain.request;

import com.raid.tickets.domain.EventStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateEventRequest {

    // We can only update an event that already exists
    private UUID id;

    private String name;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private String venue;

    private LocalDateTime salesStart;

    private LocalDateTime salesEnd;

    private EventStatusEnum status;

    @Builder.Default
    private List<UpdateTicketTypeRequest> ticketTypes = new ArrayList<>();
}
