package com.daroch.booking.dto.ticketservice.request;

import com.daroch.booking.dto.ticketservice.TicketTypeStatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.UUID;
import lombok.Data;

@Data
public class TicketServiceCreateTicketTypeRequest {

  @NotNull(message = "eventId is required")
  private UUID eventId;

  @NotBlank(message = "Ticket type name is required")
  private String name;

  @NotNull(message = "Price is required")
  @PositiveOrZero(message = "Price must be zero or greater")
  private Double price;

  private String description;

  @NotNull(message = "Total available is required")
  @PositiveOrZero(message = "Total available must be zero or greater")
  private Integer totalAvailable;

  private TicketTypeStatusEnum ticketTypeStatus;
}
