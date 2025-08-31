package com.IRCTC.Repository;

import com.IRCTC.Model.Booking;
import com.IRCTC.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // ✅ Fetch all bookings made by a particular user
    List<Booking> findByUser(User user);
}
