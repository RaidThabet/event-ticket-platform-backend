package com.raid.tickets.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTicketTypeRequest {

    private String name;

    private Integer totalAvailable;

    private Double price;

    private String description;
}
