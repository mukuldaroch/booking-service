package com.daroch.booking.dto.ticketservice.tickettype.response;

import com.daroch.booking.dto.ticketservice.tickettype.TicketTypeStatusEnum;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketServiceCreateTicketTypeResponse {

  private UUID ticketTypeId;
  private UUID eventId;
  private String name;
  private Double price;
  private String description;
  private Integer totalAvailable;
  private TicketTypeStatusEnum ticketTypeStatus;
  private LocalDateTime createdAt;
}
