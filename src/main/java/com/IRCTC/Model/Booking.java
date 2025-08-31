package com.IRCTC.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // A booking must belong to a user
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // A booking must be associated with a train
    @ManyToOne(optional = false)
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    @Column(nullable = false)
    private LocalDateTime bookingTime;

    @Min(value = 1, message = "At least 1 seat must be booked")
    @Column(nullable = false)
    private int seatsBooked;

    @Enumerated(EnumType.STRING)   // saves as CONFIRMED or CANCELLED
    @Column(nullable = false)
    private BookingStatus status;

    @PrePersist
    public void prePersist() {
        if (bookingTime == null) {
            bookingTime = LocalDateTime.now();
        }
        if (status == null) {
            status = BookingStatus.CONFIRMED;
        }
    }

    @AssertTrue(message = "Seats booked cannot exceed available seats")
    private boolean isSeatsValid() {
        if (train == null) return true;
        return seatsBooked <= train.getAvailableSeats();
    }
}
