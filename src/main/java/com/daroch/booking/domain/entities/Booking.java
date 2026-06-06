package com.daroch.booking.domain.entities;

import com.daroch.booking.domain.enums.BookingStatusEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
@Table(name = "bookings")
public class Booking {

  @Id
  @Column(name = "booking_id", nullable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID bookingId;

  @Column(name = "user_id", nullable = false)
  private UUID userId;

  @Column(name = "event_id", nullable = false)
  private UUID eventId;

  @Column(name = "total_price", nullable = false)
  private Double totalPrice;

  @Column(name = "booking_status", nullable = false)
  @Enumerated(EnumType.STRING)
  private BookingStatusEnum bookingStatus;

  // -------------------------------------------------mappings

  @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<BookingItem> items = new ArrayList<>();

  // -------------------------------------------------dates

  @CreatedDate
  @Column(name = "created_at", updatable = false, nullable = false)
  private LocalDateTime createdAt;

  @CreatedDate
  @Column(name = "expires_at", updatable = false, nullable = false)
  private LocalDateTime expiresAt;

  @LastModifiedDate
  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  // -------------------------------------------------equals & hash
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Booking)) return false;
    return bookingId != null && bookingId.equals(((Booking) o).bookingId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
