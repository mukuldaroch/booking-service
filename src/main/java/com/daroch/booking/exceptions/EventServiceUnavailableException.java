package com.daroch.booking.exceptions;

public class EventServiceUnavailableException extends RuntimeException {

  // No-arg constructor
  public EventServiceUnavailableException() {
    super();
  }

  // Constructor with message
  public EventServiceUnavailableException(String message) {
    super(message);
  }

  // Constructor with message and cause
  public EventServiceUnavailableException(String message, Throwable cause) {
    super(message, cause);
  }

  // Constructor with cause only
  public EventServiceUnavailableException(Throwable cause) {
    super(cause);
  }

  // Full constructor
  public EventServiceUnavailableException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
