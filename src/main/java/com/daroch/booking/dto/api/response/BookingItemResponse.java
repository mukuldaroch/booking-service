package com.daroch.booking.dto.api.response;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Data
public class BookingItemResponse {
  private UUID bookingItemId;
  private UUID bookingId;
  private UUID ticketTypeId;
  private int quantity;
  private float pricePerTicket;

  // -------------------------------------------------dates
  private LocalDateTime createdAt;
}
