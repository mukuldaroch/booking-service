package com.daroch.booking.domain.entities;

import com.daroch.booking.domain.enums.BookingStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "booking_status_history")
public class BookingStatusHistory {
  @Id
  @Column(name = "booking_status_Id", nullable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID bookingStatusID;

  @Column(name = "booking_Id", nullable = false, updatable = false)
  private UUID bookingId;

  @Column(name = "previous_status", nullable = false, updatable = false)
  private BookingStatusEnum previousStatus;

  @Column(name = "current_status", nullable = false, updatable = false)
  private BookingStatusEnum currentStatus;

  // -------------------------------------------------dates
  @LastModifiedDate
  @Column(name = "updated_at", nullable = false, updatable = false)
  private LocalDateTime updatedAt;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Booking)) return false;
    return bookingStatusID != null
        && bookingStatusID.equals(((BookingStatusHistory) o).bookingStatusID);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
