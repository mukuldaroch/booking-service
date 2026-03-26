package com.daroch.booking.dto.eventservice.response;

import com.daroch.booking.dto.eventservice.EventStatusEnum;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventServiceCreateEventResponse {

  private UUID eventId;
  private String name;
  private String venue;
  private EventStatusEnum status;

  private LocalDateTime eventStartDate;
  private LocalDateTime eventEndDate;
  private LocalDateTime salesStartDate;
  private LocalDateTime salesEndDate;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
