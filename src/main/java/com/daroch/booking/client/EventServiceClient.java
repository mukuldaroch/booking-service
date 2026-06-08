package com.daroch.booking.client;

import com.daroch.booking.dto.ErrorResponse;
import com.daroch.booking.dto.eventservice.response.EventServiceGetEventResponse;
import com.daroch.booking.exceptions.BusinessException;
import com.daroch.booking.exceptions.EventServiceUnavailableException;
import com.daroch.booking.exceptions.ServiceFailedException;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class EventServiceClient {

  private final WebClient eventServiceWebClient;

  public EventServiceGetEventResponse getEvent(Jwt jwt, UUID eventId) {

    try {
      return eventServiceWebClient
          .get()
          .uri("http://localhost:8083/event/{eventId}", eventId)
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
          .bodyToMono(EventServiceGetEventResponse.class)
          .block();

    } catch (WebClientRequestException ex) {
      throw new EventServiceUnavailableException();
    }
  }
}
