package com.daroch.booking.services.impl;

import com.daroch.booking.dto.api.request.CreateBookingRequest;
import com.daroch.booking.dto.api.request.UpdateBookingRequest;
import com.daroch.booking.dto.api.response.CreateBookingResponse;
import com.daroch.booking.dto.api.response.UpdateBookingResponse;
import com.daroch.booking.mappers.BookingMapper;
import com.daroch.booking.repositories.BookingRepository;
import com.daroch.booking.services.BookingCommandService;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingCommandServiceImpl implements BookingCommandService {

  private final BookingMapper bookingMapper;
  private final BookingRepository bookingRepository;

  @Transactional
  public CreateBookingResponse createBooking(CreateBookingRequest cmd) {
    // first we will make a request to the ticket service
    // varify all the ticket types
    // create BookingItem for each and caluculate total price
    // create new Booking
    // save the Booking
    return null;
  }

  public UpdateBookingResponse updateBooking(UpdateBookingRequest cmd) {
    return null;
  }

  public void deleteBooking(UUID bookingId) {}
}
