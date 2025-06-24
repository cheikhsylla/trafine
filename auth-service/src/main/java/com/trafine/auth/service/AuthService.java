package com.trafine.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AuthService {

    private final WebClient webClient;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://user-service:8080").build();
    }

    public ResponseEntity<?> authenticate(String email, String password) {
        try {
            UserResponse user = webClient.get()
                .uri("/users/by-email?email=" + email)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();

            if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }

            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while authenticating: " + e.getMessage());
        }
    }

    public ResponseEntity<?> register(RegisterRequest request) {
        try {
            // Encoder le mot de passe
            request.setPassword(passwordEncoder.encode(request.getPassword()));

            String response = webClient.post()
                .uri("/users")
                .body(Mono.just(request), RegisterRequest.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while registering: " + e.getMessage());
        }
    }

    // =================== DTOs internes ===================

    public static class UserResponse {
        private String email;
        private String password;
        private String role;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }

        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
    }

    public static class RegisterRequest {
        private String name;
        private String email;
        private String password;
        private String provider;
        private String providerId;
        private String role;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }

        public String getProvider() { return provider; }
        public void setProvider(String provider) { this.provider = provider; }

        public String getProviderId() { return providerId; }
        public void setProviderId(String providerId) { this.providerId = providerId; }

        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
    }
}
