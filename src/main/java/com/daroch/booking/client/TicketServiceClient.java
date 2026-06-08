package com.daroch.booking.client;

import com.daroch.booking.dto.ErrorResponse;
import com.daroch.booking.dto.ticketservice.response.TicketServiceTicketTypeResponse;
import com.daroch.booking.exceptions.BusinessException;
import com.daroch.booking.exceptions.ServiceFailedException;
import com.daroch.booking.exceptions.TicketServiceUnavailableException;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
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
              HttpStatusCode::is4xxClientError,
              response ->
                  response
                      .bodyToMono(ErrorResponse.class)
                      .flatMap(
                          error ->
                              Mono.error(
                                  new BusinessException(
                                      error.getErrorCode(),
                                      error.getMessage(),
                                      HttpStatus.valueOf(error.getStatus())))))
          // 5xx — Event Service failed
          .onStatus(
              HttpStatusCode::is5xxServerError,
              response ->
                  response
                      .bodyToMono(ErrorResponse.class)
                      .flatMap(
                          error ->
                              Mono.error(
                                  new ServiceFailedException(
                                      error.getErrorCode(), error.getMessage()))))
          .bodyToFlux(TicketServiceTicketTypeResponse.class)
          .collectList()
          .block();

    } catch (WebClientRequestException ex) {

      throw new TicketServiceUnavailableException();
    }
  }
}
