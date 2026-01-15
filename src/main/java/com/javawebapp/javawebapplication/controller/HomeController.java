package com.javawebapp.javawebapplication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class HomeController {

    @GetMapping("/home")
    public ResponseEntity<?> home() {
        // Get the authenticated user from security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();

            Map<String, String> response = new HashMap<>();
            response.put("message", "Welcome to the protected home page!");
            response.put("user", username);
            response.put("status", "authenticated");

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("Unauthorized");
        }
    }
    @GetMapping("/api/profile")
    public ResponseEntity<?> profile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();

            Map<String, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("authorities", authentication.getAuthorities());
            response.put("authenticated", true);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("Unauthorized");
        }
    }
}