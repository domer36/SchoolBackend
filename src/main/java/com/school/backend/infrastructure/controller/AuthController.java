package com.school.backend.infrastructure.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.backend.infrastructure.dto.auth.LoginRequest;
import com.school.backend.infrastructure.security.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        if (!request.getEmail().equals("admin@example.com") || 
            !request.getPassword().equals("admin123")) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtService.generateToken(request.getEmail(), "ADMIN");
    }
}
