package com.javawebapp.javawebapplication.controller;


import com.javawebapp.javawebapplication.model.Users;
import com.javawebapp.javawebapplication.service.UserService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data

public class RegisterRequest {
    private String username;
    private String password;
    private String email;

    public ResponseEntity<?> handle(UserService userService) {
        try {
            if (username == null || username.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Username is empty");
            }
            if (password == null || password.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Password is empty");
            }

            Users user = new Users();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);

            userService.register(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (RuntimeException e){
            if (e.getMessage().contains("Username already exist")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Registration failed: Username already exist");
            }
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed: " + e.getMessage());
        }
    }

}
