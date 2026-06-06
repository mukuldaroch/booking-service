package com.daroch.booking.services;

import com.daroch.booking.dto.api.request.CreateBookingRequest;
import com.daroch.booking.dto.api.request.UpdateBookingRequest;
import com.daroch.booking.dto.api.response.CreateBookingResponse;
import com.daroch.booking.dto.api.response.UpdateBookingResponse;
import java.util.UUID;
import org.springframework.security.oauth2.jwt.Jwt;

public interface BookingCommandService {

  public CreateBookingResponse createBooking(Jwt jwt, CreateBookingRequest cmd);

  public UpdateBookingResponse updateBooking(UpdateBookingRequest cmd);

  public void deleteBooking(UUID bookingId);
}
