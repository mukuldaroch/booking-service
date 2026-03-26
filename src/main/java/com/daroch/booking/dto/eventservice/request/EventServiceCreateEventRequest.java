package com.daroch.booking.dto.eventservice.request;

import com.daroch.booking.dto.eventservice.EventStatusEnum;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventServiceCreateEventRequest {

  private String name;
  private String venue;
  private EventStatusEnum status;

  private LocalDateTime eventStartDate;
  private LocalDateTime eventEndDate;
  private LocalDateTime salesStartDate;
  private LocalDateTime salesEndDate;
}
