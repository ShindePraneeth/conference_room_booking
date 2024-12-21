package com.indium.conference.controller;

import com.indium.conference.entity.UserDetails;
import com.indium.conference.repository.UserDetailsRepository;
import com.indium.conference.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private JWTUtil jwtUtil;

    // Endpoint to login and generate token
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String userId, @RequestParam String password) {
        Optional<UserDetails> userOptional = userDetailsRepository.findById(userId);

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(401).body("Invalid User ID.");
        }

        UserDetails user = userOptional.get();

        if (!user.getPassword().equals(password)) {
            return ResponseEntity.status(401).body("Invalid Password.");
        }

        // Generate JWT Token
        String token = jwtUtil.generateToken(user.getUserId(), user.getRoleType());

        return ResponseEntity.ok("Bearer " + token);
    }

    // Validate the token
    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");
        if (jwtUtil.validateToken(token)) {
            return ResponseEntity.ok("Token is valid.");
        } else {
            return ResponseEntity.status(401).body("Invalid or Expired Token.");
        }
    }
}
