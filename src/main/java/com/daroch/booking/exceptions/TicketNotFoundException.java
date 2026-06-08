package com.daroch.booking.exceptions;

import org.springframework.http.HttpStatus;

public class TicketNotFoundException extends BusinessException {

  public TicketNotFoundException() {
    super("TICKET_NOT_FOUND", "Ticket not found", HttpStatus.NOT_FOUND);
  }
}
