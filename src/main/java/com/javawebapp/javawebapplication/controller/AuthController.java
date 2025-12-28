package com.javawebapp.javawebapplication.controller;

import com.javawebapp.javawebapplication.model.User;
import com.javawebapp.javawebapplication.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository , PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
        if(this.userRepository.findByEmail(request.getEmail()) != null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This email already used!");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PostMapping("/log-in")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail());
        if(user == null ||!(passwordEncoder.matches(request.getPassword(), user.getPassword()))){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password!");
        }
        return ResponseEntity.ok(user);
    }

}
