package com.daroch.booking.client;

import com.daroch.booking.dto.ticketservice.response.TicketServiceTicketTypeResponse;
import com.daroch.booking.exceptions.BusinessException;
import com.daroch.booking.exceptions.TicketServiceUnavailableException;
import com.daroch.booking.exceptions.TicketTypeNotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class TicketServiceClient {

  private final WebClient webClient;

  public TicketServiceClient(WebClient webClient) {
    this.webClient = webClient;
  }

  public List<TicketServiceTicketTypeResponse> getTicketTypes(Jwt jwt, UUID eventId) {

    try {
      return webClient
          .get()
          .uri("http://localhost:8084/ticket-type?eventId={eventId}", eventId)
          .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt.getTokenValue())
          .retrieve()

          // 4xx — client & business errors
          .onStatus(
              status -> status.value() == 400,
              r -> Mono.error(new BusinessException("Invalid event id")))
          .onStatus(
              status -> status.value() == 404,
              r -> Mono.error(new TicketTypeNotFoundException("Event not found")))
          // 5xx — Ticket Service exploded internally
          .onStatus(
              HttpStatusCode::is5xxServerError,
              r -> r.bodyToMono(String.class).map(TicketServiceUnavailableException::new))
          .bodyToFlux(TicketServiceTicketTypeResponse.class)
          .collectList()
          .block();

    } catch (Exception ex) {
      ex.printStackTrace();
      throw new TicketServiceUnavailableException("Ticket service unreachable", ex);
    }
  }
}
