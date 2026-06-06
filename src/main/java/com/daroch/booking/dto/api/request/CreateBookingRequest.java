package com.daroch.booking.dto.api.request;

import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class CreateBookingRequest {

  private UUID eventId;
  private List<BookingItemRequest> items;
}
