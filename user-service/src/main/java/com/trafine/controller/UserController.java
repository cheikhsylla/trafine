package com.trafine.controller;

import com.trafine.model.User;
import com.trafine.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint de validation des identifiants
    @PostMapping("/validate")
    public ResponseEntity<?> validate(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String password = payload.get("password");

        try {
            Map<String, Object> result = userService.validateCredentials(email, password);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }
    }

    // Endpoint de création d'utilisateur
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User saved = userService.createUser(user);
            return ResponseEntity.status(201).body(saved);
        } catch (Exception e) {
            // Optionnel : message plus précis en cas d'e-mail déjà utilisé
            if (e.getMessage().contains("ConstraintViolationException") || e.getMessage().contains("duplicate key")) {
                return ResponseEntity.status(409).body("Email already exists");
            }
            return ResponseEntity.status(500).body("Error creating user: " + e.getMessage());
        }
    }
}
