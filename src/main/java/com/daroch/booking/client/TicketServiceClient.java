package com.daroch.booking.client;

import com.daroch.booking.dto.ticketservice.tickettype.request.TicketServiceCreateTicketTypeRequest;
import com.daroch.booking.dto.ticketservice.tickettype.response.TicketServiceCreateTicketTypeResponse;
import com.daroch.booking.exceptions.BusinessException;
import com.daroch.booking.exceptions.TicketServiceUnavailableException;
import com.daroch.booking.exceptions.TicketTypeNotFoundException;
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

  public TicketServiceCreateTicketTypeResponse createTicketType(
      Jwt jwt, TicketServiceCreateTicketTypeRequest request) {

    try {
      return webClient
          .post()
          .uri("http://localhost:8084/ticket-types")
          .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt.getTokenValue())
          .bodyValue(request)
          .retrieve()

          // 4xx — client & business errors
          .onStatus(
              status -> status.value() == 400,
              r -> Mono.error(new BusinessException("Invalid ticket type request")))
          .onStatus(
              status -> status.value() == 404,
              r -> Mono.error(new TicketTypeNotFoundException("Ticket or Event not found")))

          // 5xx — Ticket Service exploded internally
          .onStatus(
              HttpStatusCode::is5xxServerError,
              r -> r.bodyToMono(String.class).map(TicketServiceUnavailableException::new))
          .bodyToMono(TicketServiceCreateTicketTypeResponse.class)
          .block();

    } catch (Exception ex) {
      throw new TicketServiceUnavailableException("Ticket service unreachable", ex);
    }
  }
}
