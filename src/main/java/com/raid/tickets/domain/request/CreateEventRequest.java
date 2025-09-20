package com.raid.tickets.domain.request;

import com.raid.tickets.domain.EventStatusEnum;
import com.raid.tickets.domain.entities.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateEventRequest {

    private String name;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private String venue;

    private LocalDateTime salesStart;

    private LocalDateTime salesEnd;

    private EventStatusEnum status;

    private List<CreateTicketTypeRequest> ticketTypes = new ArrayList<>();
}
