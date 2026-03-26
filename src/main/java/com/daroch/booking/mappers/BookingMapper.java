package com.daroch.booking.mappers;

import com.daroch.booking.domain.entities.Booking;
import com.daroch.booking.dto.api.response.CreateBookingResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingMapper {

  CreateBookingResponse toCreateBookingResponse(Booking booking);
}
