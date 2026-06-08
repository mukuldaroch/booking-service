package com.daroch.booking.exceptions;

import org.springframework.http.HttpStatus;

public class TicketTypeNotFoundException extends BusinessException {

  public TicketTypeNotFoundException() {
    super("TICKET_TYPE_NOT_FOUND", "Ticket Type not found", HttpStatus.NOT_FOUND);
  }
}
