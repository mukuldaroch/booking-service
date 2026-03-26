package com.daroch.booking.exceptions;

public class TicketServiceUnavailableException extends RuntimeException {

  // No-arg constructor
  public TicketServiceUnavailableException() {
    super();
  }

  // Constructor with message
  public TicketServiceUnavailableException(String message) {
    super(message);
  }

  // Constructor with message and cause
  public TicketServiceUnavailableException(String message, Throwable cause) {
    super(message, cause);
  }

  // Constructor with cause only
  public TicketServiceUnavailableException(Throwable cause) {
    super(cause);
  }

  // Full constructor
  public TicketServiceUnavailableException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
