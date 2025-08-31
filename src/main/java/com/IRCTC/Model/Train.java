package com.IRCTC.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "train")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Train name is required")
    @Column(nullable = false)
    private String trainName;

    @NotBlank(message = "Source station is required")
    @Column(nullable = false)
    private String source;

    @NotBlank(message = "Destination station is required")
    @Column(nullable = false)
    private String destination;

    @Min(value = 1, message = "Total seats must be at least 1")
    @Column(nullable = false)
    private int totalSeats;

    @Min(value = 0, message = "Available seats cannot be negative")
    @Column(nullable = false)
    private int availableSeats;

    // One train can have many bookings
    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Booking> bookings = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (availableSeats == 0) {
            availableSeats = totalSeats;
        }
    }

    @AssertTrue(message = "Source and destination cannot be the same")
    private boolean isSourceDestinationValid() {
        if (source == null || destination == null) return true;
        return !source.equalsIgnoreCase(destination);
    }
}
