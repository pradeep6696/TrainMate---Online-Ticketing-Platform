package com.IRCTC.Controllers;

import com.IRCTC.Model.User;
import com.IRCTC.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

        @Autowired
        private UserService userService;

        // ✅ User registration
        @PostMapping
        public ResponseEntity<User> registerUser(@RequestBody User user) {
                User savedUser = userService.saveUser(user);
                return ResponseEntity.ok(savedUser);
        }

        // ✅ Get all users (Admin purpose)
        @GetMapping
        public List<User> getAllUsers() {
                return userService.getAllUsers();
        }

        // ✅ Get user by email
        @GetMapping("/{email}")
        public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
                return userService.getUserByEmail(email)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
        }

        // ✅ Delete user
        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteUser(@PathVariable Long id) {
                userService.deleteUser(id);
                return ResponseEntity.ok("User deleted successfully!");
        }
}
