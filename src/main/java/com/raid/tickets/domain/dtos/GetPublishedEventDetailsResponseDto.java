package com.raid.tickets.domain.dtos;

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
public class GetPublishedEventDetailsResponseDto {

    private UUID id;

    private String name;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private String venue;

    @Builder.Default
    private List<GetPublishedEventDetailsTicketTypesResponseDto> ticketTypes = new ArrayList<>();
}
