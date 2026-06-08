package com.daroch.booking.exceptions;

public class EventServiceUnavailableException extends ServiceUnavailableException {

  public EventServiceUnavailableException() {
    super("EVENT_SERVICE_UNAVAILABLE", "Event Service is unavailable at the time");
  }
  ;
}
