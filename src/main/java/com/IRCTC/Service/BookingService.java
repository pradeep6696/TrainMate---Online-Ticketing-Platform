package com.IRCTC.Service;

import com.IRCTC.Model.Booking;
import com.IRCTC.Model.BookingStatus;
import com.IRCTC.Model.Train;
import com.IRCTC.Model.User;
import com.IRCTC.Repository.BookingRepository;
import com.IRCTC.Repository.TrainRepository;
import com.IRCTC.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final TrainRepository trainRepository;

    public BookingService(BookingRepository bookingRepository,
                          UserRepository userRepository,
                          TrainRepository trainRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.trainRepository = trainRepository;
    }

    // ✅ Create Booking (fetch User & Train first)
    @Transactional
    public Booking createBooking(Booking booking) {
        Long userId = booking.getUser().getId();
        Long trainId = booking.getTrain().getId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Train train = trainRepository.findById(trainId)
                .orElseThrow(() -> new RuntimeException("Train not found with ID: " + trainId));

        booking.setUser(user);
        booking.setTrain(train);
        booking.setStatus(BookingStatus.CONFIRMED); // default

        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingByIdOrThrow(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + id));
    }

    @Transactional
    public Booking cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(BookingStatus.CANCELLED);
        return bookingRepository.save(booking);
    }
}
