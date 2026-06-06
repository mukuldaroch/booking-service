package com.daroch.booking.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "booking_items")
public class BookingItem {

  @Id
  @Column(name = "booking_item_id", nullable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID bookingItemId;

  @Column(name = "ticket_type_id", nullable = false, updatable = false)
  private UUID ticketTypeId;

  @Column(name = "quantity", nullable = false, updatable = false)
  private int quantity;

  @Column(name = "price_per_ticket", nullable = false, updatable = false)
  private Double pricePerTicket;

  // -------------------------------------------------mappings
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "booking_id", nullable = false)
  private Booking booking;

  // -------------------------------------------------dates
  @CreatedDate
  @Column(name = "created_at", updatable = false, nullable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  // -------------------------------------------------equals & hash
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BookingItem)) return false;
    return bookingItemId != null && bookingItemId.equals(((BookingItem) o).bookingItemId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
