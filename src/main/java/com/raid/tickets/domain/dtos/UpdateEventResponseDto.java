package com.raid.tickets.domain.dtos;

import com.raid.tickets.domain.EventStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateEventResponseDto {

    private UUID id;

    private String name;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private String venue;

    private LocalDateTime salesStart;

    private LocalDateTime salesEnd;

    private EventStatusEnum status;

    private List<UpdateTicketTypeResponseDto> ticketTypes;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
