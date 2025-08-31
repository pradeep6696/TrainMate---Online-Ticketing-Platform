package com.IRCTC.Service;

import com.IRCTC.Model.User;
import com.IRCTC.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    // ✅ Constructor Injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Add new user (default role = ROLE_USER)
    public User saveUser(User user) {
        // For now, store password as plain text (⚠️ not secure in real projects)
        user.setRole("ROLE_USER");  // force role
        return userRepository.save(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Delete user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
