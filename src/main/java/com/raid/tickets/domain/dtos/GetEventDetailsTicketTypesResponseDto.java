package com.raid.tickets.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetEventDetailsTicketTypesResponseDto {

    private UUID id;

    private String name;

    private Integer totalAvailable;

    private Double price;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime lastUpdatedAt;
}
