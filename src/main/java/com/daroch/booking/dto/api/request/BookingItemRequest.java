package com.daroch.booking.dto.api.request;

import java.util.UUID;
import lombok.Data;

@Data
public class BookingItemRequest {

  private UUID ticketTypeId;
  private int quantity;
}
