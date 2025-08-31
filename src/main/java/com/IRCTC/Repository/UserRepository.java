package com.IRCTC.Repository;

import com.IRCTC.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // ✅ Fetch a user by email
    Optional<User> findByEmail(String email);

    // ✅ Check if email already exists
    boolean existsByEmail(String email);

    // ✅ Check if phone number already exists
    boolean existsByPhone(String phone);
}
