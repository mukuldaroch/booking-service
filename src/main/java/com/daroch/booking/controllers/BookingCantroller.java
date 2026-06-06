package com.daroch.booking.controllers;

import com.daroch.booking.dto.api.request.CreateBookingRequest;
import com.daroch.booking.dto.api.response.CreateBookingResponse;
import com.daroch.booking.services.BookingCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/booking")
@RequiredArgsConstructor
public class BookingCantroller {

  private final BookingCommandService bookingCommandService;

  @PostMapping
  public CreateBookingResponse createBooking(
      @AuthenticationPrincipal Jwt jwt, @RequestBody CreateBookingRequest bookingRequest) {

    return bookingCommandService.createBooking(jwt, bookingRequest);
  }

  @GetMapping
  public String testing() {
    System.out.println("GET /booking hit");
    return "this is working";
  }
}
