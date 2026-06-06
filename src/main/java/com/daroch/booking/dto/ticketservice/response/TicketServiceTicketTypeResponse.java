package com.daroch.booking.dto.ticketservice.response;

import com.daroch.booking.dto.ticketservice.TicketTypeStatusEnum;
import java.util.UUID;
import lombok.Data;

@Data
public class TicketServiceTicketTypeResponse {

  private UUID ticketTypeId;
  private UUID eventId;
  private String name;
  private Double price;
  private String description;
  private Integer totalAvailable;
  private TicketTypeStatusEnum status;
}
