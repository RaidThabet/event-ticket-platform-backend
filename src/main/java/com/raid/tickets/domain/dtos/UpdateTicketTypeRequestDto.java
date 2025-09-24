package com.raid.tickets.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTicketTypeRequestDto {

    @org.hibernate.validator.constraints.UUID(message = "Id format is invalid")
    private UUID id;

    @NotBlank(message = "Ticket type name is required")
    private String name;

    private Integer totalAvailable;

    @NotNull(message = "Price is required")
    @PositiveOrZero(message =  "Price must be zero or greater")
    private Double price;

    private String description;
}
