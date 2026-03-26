package com.daroch.booking.dto.api.request;

import com.daroch.booking.domain.enums.BookingStatusEnum;
import lombok.Data;

@Data
public class UpdateBookingRequest {

  private BookingStatusEnum bookingStatus;
}
