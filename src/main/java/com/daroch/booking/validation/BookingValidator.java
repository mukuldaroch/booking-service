package com.daroch.booking.validation;

import com.daroch.booking.dto.api.request.CreateBookingRequest;
import com.daroch.booking.dto.eventservice.response.EventServiceGetEventResponse;
import com.daroch.booking.dto.ticketservice.response.TicketServiceTicketTypeResponse;
import com.daroch.booking.exceptions.BadRequestException;
import com.daroch.booking.exceptions.InvalidTicketTypeException;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class BookingValidator {

  public void validateRequestEvent(
      CreateBookingRequest request, EventServiceGetEventResponse eventResponse) {

    if (request == null) {
      throw new BadRequestException("Request cannot be null");
    }

    if (request.getItems() == null || request.getItems().isEmpty()) {
      throw new BadRequestException("At least one booking item is required");
    }
    if (request.getEventId() == null) {
      throw new BadRequestException("EventId is required");
    }
  }

  public void validateRequestTicketTypes(
      CreateBookingRequest request, List<TicketServiceTicketTypeResponse> availableTicketTypes) {

    if (request == null) {
      throw new BadRequestException("Request cannot be null");
    }

    if (request.getItems() == null || request.getItems().isEmpty()) {
      throw new BadRequestException("At least one booking item is required");
    }

    Set<UUID> availableTicketTypeIds =
        availableTicketTypes.stream()
            .map(TicketServiceTicketTypeResponse::getTicketTypeId)
            .collect(Collectors.toSet());

    request
        .getItems()
        .forEach(
            item -> {
              if (item.getTicketTypeId() == null) {
                throw new BadRequestException("Ticket type id is required");
              }

              if (item.getQuantity() <= 0) {
                throw new BadRequestException("Quantity must be greater than 0");
              }

              if (!availableTicketTypeIds.contains(item.getTicketTypeId())) {
                throw new InvalidTicketTypeException(
                    "InvalidTicketTypeException" + item.getTicketTypeId().toString());
              }
            });
  }
}
