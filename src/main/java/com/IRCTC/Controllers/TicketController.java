package com.IRCTC.Controllers;

import com.IRCTC.Model.Booking;
import com.IRCTC.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class TicketController {

    @Autowired
    private BookingService bookingService;

    // ✅ User books ticket
    @PostMapping
    public ResponseEntity<Booking> bookTicket(@RequestBody Booking booking) {
        Booking savedBooking = bookingService.createBooking(booking);
        return ResponseEntity.ok(savedBooking);
    }

    // ✅ User cancels ticket
    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelTicket(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.ok("Ticket cancelled successfully!");
    }

    // ✅ Admin sees all bookings
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
}
