package com.daroch.booking.exceptions;

public class TicketNotFoundException extends RuntimeException {

  // No-arg constructor
  public TicketNotFoundException() {
    super();
  }

  // Constructor with message
  public TicketNotFoundException(String message) {
    super(message);
  }

  // Constructor with message and cause
  public TicketNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  // Constructor with cause only
  public TicketNotFoundException(Throwable cause) {
    super(cause);
  }

  // Full constructor
  public TicketNotFoundException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
