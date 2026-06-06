package com.daroch.booking.dto.api.response;

import com.daroch.booking.domain.enums.BookingStatusEnum;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class CreateBookingResponse {

  private UUID bookingId;
  private UUID eventId;
  private float totalPrice;
  private BookingStatusEnum bookingStatus;
  private List<BookingItemResponse> items;

  // -------------------------------------------------dates
  // private LocalDateTime createdAt;
  private LocalDateTime expiresAt;
}
