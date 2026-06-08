package com.daroch.booking.exceptions;

import org.springframework.http.HttpStatus;

public class BookingNotFoundException extends BusinessException {

  public BookingNotFoundException() {
    super("BOOKING_NOT_FOUND", "Booking not found", HttpStatus.NOT_FOUND);
  }
}
