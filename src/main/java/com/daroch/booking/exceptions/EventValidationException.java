package com.daroch.booking.exceptions;

public class EventValidationException extends RuntimeException {

  // No-arg constructor
  public EventValidationException() {
    super();
  }

  // Constructor with message
  public EventValidationException(String message) {
    super(message);
  }

  // Constructor with message and cause
  public EventValidationException(String message, Throwable cause) {
    super(message, cause);
  }

  // Constructor with cause only
  public EventValidationException(Throwable cause) {
    super(cause);
  }

  // Full constructor
  public EventValidationException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
