package com.daroch.booking.client;

import com.daroch.booking.dto.eventservice.response.EventServiceGetEventResponse;
import com.daroch.booking.exceptions.EventNotFoundException;
import com.daroch.booking.exceptions.EventServiceFailedException;
import com.daroch.booking.exceptions.EventServiceUnavailableException;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class EventServiceClient {

  private final WebClient eventServiceWebClient;

  public EventServiceGetEventResponse getEvent(Jwt jwt, UUID eventId) {

    try {
      return eventServiceWebClient
          .get()
          .uri("http://localhost:8083/events/{eventId}", eventId)
          .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt.getTokenValue())
          .retrieve()

          // 4xx — client & business errors
          .onStatus(
              status -> status.value() == 404,
              r -> Mono.error(new EventNotFoundException("Event not found")))

          // 5xx — Event Service failed
          .onStatus(
              HttpStatusCode::is5xxServerError,
              r -> r.bodyToMono(String.class).map(EventServiceFailedException::new))
          .bodyToMono(EventServiceGetEventResponse.class)
          .block();

    } catch (Exception ex) {
      ex.printStackTrace();
      throw new EventServiceUnavailableException("Event service unreachable", ex);
    }
  }
}
