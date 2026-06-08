package com.daroch.booking.controllers;

import com.daroch.booking.dto.ErrorResponse;
import com.daroch.booking.dto.api.request.CreateBookingRequest;
import com.daroch.booking.dto.api.response.CreateBookingResponse;
import com.daroch.booking.services.BookingCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/booking")
@RequiredArgsConstructor
public class BookingCantroller {

  private final BookingCommandService bookingCommandService;

  @Operation(summary = "Create Booking")
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "Booking Created"),
    @ApiResponse(
        responseCode = "400",
        description = "Invalid request",
        content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
  })
  @PostMapping
  public ResponseEntity<CreateBookingResponse> createBooking(
      @AuthenticationPrincipal Jwt jwt, @RequestBody CreateBookingRequest bookingRequest) {

    CreateBookingResponse bookingResponse =
        bookingCommandService.createBooking(jwt, bookingRequest);
    return ResponseEntity.ok(bookingResponse);
  }
}
