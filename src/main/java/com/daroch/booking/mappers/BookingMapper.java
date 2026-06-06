package com.daroch.booking.mappers;

import com.daroch.booking.domain.entities.Booking;
import com.daroch.booking.domain.entities.BookingItem;
import com.daroch.booking.dto.api.request.BookingItemRequest;
import com.daroch.booking.dto.api.request.CreateBookingRequest;
import com.daroch.booking.dto.api.response.BookingItemResponse;
import com.daroch.booking.dto.api.response.CreateBookingResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingMapper {
  CreateBookingResponse toCreateBookingResponse(Booking booking);

  Booking toBooking(CreateBookingRequest request);

  BookingItem toBookingItem(BookingItemRequest request);

  BookingItemResponse toBookingItemResponse(BookingItem bookingItem);
}
