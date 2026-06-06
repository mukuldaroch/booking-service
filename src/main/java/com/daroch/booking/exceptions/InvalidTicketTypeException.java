package com.daroch.booking.exceptions;

public class InvalidTicketTypeException extends RuntimeException {

  // No-arg constructor
  public InvalidTicketTypeException() {
    super();
  }

  // Constructor with message
  public InvalidTicketTypeException(String message) {
    super(message);
  }

  // Constructor with message and cause
  public InvalidTicketTypeException(String message, Throwable cause) {
    super(message, cause);
  }

  // Constructor with cause only
  public InvalidTicketTypeException(Throwable cause) {
    super(cause);
  }

  // Full constructor
  public InvalidTicketTypeException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
