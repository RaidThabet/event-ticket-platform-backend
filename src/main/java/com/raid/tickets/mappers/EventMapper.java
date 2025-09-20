package com.raid.tickets.mappers;

import com.raid.tickets.domain.dtos.CreateEventRequestDto;
import com.raid.tickets.domain.dtos.CreateEventResponseDto;
import com.raid.tickets.domain.dtos.CreateTicketTypeRequestDto;
import com.raid.tickets.domain.entities.Event;
import com.raid.tickets.domain.request.CreateEventRequest;
import com.raid.tickets.domain.request.CreateTicketTypeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);

    CreateEventRequest fromDto(CreateEventRequestDto dto);

    CreateEventResponseDto toDto(Event event);
}
