package com.raid.tickets.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTicketTypeRequest {

    // If id is null, that means we added a new ticket type for the event.
    // If id is not null, that means we modified an exiting ticket type for the event.
    private UUID id;

    private String name;

    private Integer totalAvailable;

    private Double price;

    private String description;
}
