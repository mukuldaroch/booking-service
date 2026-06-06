package com.daroch.booking.dto.eventservice.response;

import com.daroch.booking.dto.eventservice.EventStatusEnum;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Data
public class EventServiceGetEventResponse {

  private UUID eventId;
  private String name;
  private String venue;
  private EventStatusEnum status;

  private LocalDateTime eventStartDate;
  private LocalDateTime eventEndDate;
  private LocalDateTime salesStartDate;
  private LocalDateTime salesEndDate;
}
