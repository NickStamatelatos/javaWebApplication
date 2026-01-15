package com.javawebapp.javawebapplication.controller;

import com.javawebapp.javawebapplication.model.Users;
import com.javawebapp.javawebapplication.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Users user) {
        try {

            if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Username is required");
            }
            if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Password is required");
            }

            Users registeredUser = userService.register(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (RuntimeException e) {
            if (e.getMessage().contains("Username already exists")) {
                return ResponseEntity.badRequest().body("Registration failed: Username already exists");
            }
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users user) {
        try {

            if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
                return ResponseEntity.status(401).body("Username is required");
            }
            if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
                return ResponseEntity.status(401).body("Password is required");
            }

            String token = userService.verify(user);

            if (!"authentication failed".equals(token)) {
                return ResponseEntity.ok(new TokenResponse(token));
            } else {
                return ResponseEntity.status(401).body("Invalid credentials");
            }
        } catch (BadCredentialsException e) {

            return ResponseEntity.status(401).body("Invalid username or password");
        } catch (Exception e) {

            return ResponseEntity.status(401).body("Authentication failed");
        }
    }
}

@Setter
@Getter
class TokenResponse {
    private String token;

    public TokenResponse(String token) {
        this.token = token;
    }

}