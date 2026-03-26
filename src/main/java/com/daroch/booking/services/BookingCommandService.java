package com.daroch.booking.services;

import com.daroch.booking.dto.api.request.CreateBookingRequest;
import com.daroch.booking.dto.api.request.UpdateBookingRequest;
import com.daroch.booking.dto.api.response.CreateBookingResponse;
import com.daroch.booking.dto.api.response.UpdateBookingResponse;
import java.util.UUID;

public interface BookingCommandService {

  public CreateBookingResponse createBooking(CreateBookingRequest cmd);

  public UpdateBookingResponse updateBooking(UpdateBookingRequest cmd);

  public void deleteBooking(UUID bookingId);
}
