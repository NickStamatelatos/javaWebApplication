package com.javawebapp.javawebapplication.controller;

import com.javawebapp.javawebapplication.model.Users;
import com.javawebapp.javawebapplication.service.UserService;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

@Data
public class LoginRequest {
    private String username;
    private String password;


    public ResponseEntity<?> handle(UserService userService){
        try {
            if(username == null || username.trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username is required");
            }
            if(password == null || password.trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password is required");
            }
            Users user = new Users();
            user.setUsername(username);
            user.setPassword(password);

            String token = userService.verify(user);
            if (!"authentication failed".equals(token)) {
                return ResponseEntity.ok(new TokenResponse(token));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
            }
        } catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bad credentials");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: " + e.getMessage());
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
