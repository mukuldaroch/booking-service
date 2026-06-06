package com.daroch.booking.services.impl;

import com.daroch.booking.client.EventServiceClient;
import com.daroch.booking.client.TicketServiceClient;
import com.daroch.booking.domain.entities.Booking;
import com.daroch.booking.domain.entities.BookingItem;
import com.daroch.booking.domain.enums.BookingStatusEnum;
import com.daroch.booking.dto.api.request.CreateBookingRequest;
import com.daroch.booking.dto.api.request.UpdateBookingRequest;
import com.daroch.booking.dto.api.response.CreateBookingResponse;
import com.daroch.booking.dto.api.response.UpdateBookingResponse;
import com.daroch.booking.dto.eventservice.response.EventServiceGetEventResponse;
import com.daroch.booking.dto.ticketservice.response.TicketServiceTicketTypeResponse;
import com.daroch.booking.mappers.BookingMapper;
import com.daroch.booking.repositories.BookingRepository;
import com.daroch.booking.services.BookingCommandService;
import com.daroch.booking.validation.BookingValidator;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingCommandServiceImpl implements BookingCommandService {

  private final BookingMapper bookingMapper;
  private final BookingRepository bookingRepository;
  private final BookingValidator bookingValidator;
  private final EventServiceClient eventServiceClient;
  private final TicketServiceClient ticketServiceClient;

  @Transactional
  public CreateBookingResponse createBooking(Jwt jwt, CreateBookingRequest bookingRequest) {

    UUID userId = UUID.fromString(jwt.getSubject());

    // jwt.getClaimAsString("role"); // USER

    // 1. Fetch required data
    // - call Event Service
    EventServiceGetEventResponse eventServiceResponse =
        eventServiceClient.getEvent(jwt, bookingRequest.getEventId());

    // - call Ticket Service
    List<TicketServiceTicketTypeResponse> ticketServiceTicketTypeResponses =
        ticketServiceClient.getTicketTypes(jwt, bookingRequest.getEventId());

    // 3. Verify business rules
    // - event exists
    // - event is active
    bookingValidator.validateRequestEvent(bookingRequest, eventServiceResponse);

    // - ticket type exists
    // - ticket belongs to event
    bookingValidator.validateRequestTicketTypes(bookingRequest, ticketServiceTicketTypeResponses);
    // - enough tickets available

    // 4. Calculate pricing
    // - total price
    // - taxes
    // - discounts
    Map<UUID, Double> prices =
        ticketServiceTicketTypeResponses.stream()
            .collect(
                Collectors.toMap(
                    TicketServiceTicketTypeResponse::getTicketTypeId,
                    TicketServiceTicketTypeResponse::getPrice));

    Double totalPrice =
        bookingRequest.getItems().stream()
            .mapToDouble(item -> prices.get(item.getTicketTypeId()) * item.getQuantity())
            .sum();

    // 5. Create booking aggregate

    // - Booking
    Booking newBooking =
        Booking.builder()
            .userId(userId)
            .eventId(bookingRequest.getEventId())
            .totalPrice(totalPrice)
            .bookingStatus(BookingStatusEnum.PENDING)
            .build();

    // - BookingItems
    List<BookingItem> bookingItems =
        bookingRequest.getItems().stream()
            .map(
                item ->
                    BookingItem.builder()
                        .ticketTypeId(item.getTicketTypeId())
                        .quantity(item.getQuantity())
                        .pricePerTicket(prices.get(item.getTicketTypeId()).doubleValue())
                        .booking(newBooking)
                        .build())
            .collect(Collectors.toList());

    newBooking.setItems(bookingItems);

    // 6. Persist booking
    Booking savedBooking = bookingRepository.save(newBooking);

    // 7. Publish booking created event
    // TODO: add kafka integration in this

    // 8. Return response
    return bookingMapper.toCreateBookingResponse(savedBooking);
  }

  public UpdateBookingResponse updateBooking(UpdateBookingRequest cmd) {
    return null;
  }

  public void deleteBooking(UUID bookingId) {}
}
