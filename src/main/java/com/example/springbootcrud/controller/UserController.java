package com.example.springbootcrud.controller;

import com.example.springbootcrud.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/users")
public class UserController {

    // In-memory data store for quick, self-contained CRUD operations
    private final ConcurrentHashMap<Long, User> userStorage = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(3);

    public UserController() {
        // Pre-populating default data
        userStorage.put(1L, new User(1L, "Rajesh Kumar", "rajesh@example.com"));
        userStorage.put(2L, new User(2L, "Sita Sharma", "sita@example.com"));
        userStorage.put(3L, new User(3L, "Kiran Varma", "kiran@example.com"));
    }

    // 1. READ ALL (GET)
    @GetMapping
    public List<User> getAllUsers() {
        return new ArrayList<>(userStorage.values());
    }

    // 2. READ BY ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        User user = userStorage.get(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: User not found with ID " + id);
        }
        return ResponseEntity.ok(user);
    }

    // 3. CREATE (POST)
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User newUser) {
        if (newUser.getName() == null || newUser.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Error: User name is required.");
        }
        long newId = idCounter.incrementAndGet();
        newUser.setId(newId);
        userStorage.put(newId, newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    // 4. UPDATE (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User existingUser = userStorage.get(id);
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: Cannot update. User not found with ID " + id);
        }

        if (updatedUser.getName() != null) {
            existingUser.setName(updatedUser.getName());
        }
        if (updatedUser.getEmail() != null) {
            existingUser.setEmail(updatedUser.getEmail());
        }

        userStorage.put(id, existingUser);
        return ResponseEntity.ok(existingUser);
    }

    // 5. DELETE (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        User removedUser = userStorage.remove(id);
        if (removedUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: User with ID " + id + " does not exist.");
        }
        return ResponseEntity.ok("Success: User '" + removedUser.getName() + "' with ID " + id + " deleted successfully.");
    }
}
