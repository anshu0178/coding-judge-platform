package com.anshu.codingjudge.authservice.controller;

import com.anshu.codingjudge.authservice.dto.AuthResponse;
import com.anshu.codingjudge.authservice.dto.LoginRequest;
import com.anshu.codingjudge.authservice.dto.RegisterRequest;
import com.anshu.codingjudge.authservice.security.JwtService;
import com.anshu.codingjudge.authservice.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;



    @GetMapping("/profile")
    public String profile(Authentication authentication) {

        return authentication.getName();
    }

    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public String register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return authService.registerUser(request);
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest request) {

        return authService.login(request);
    }

    @GetMapping("/test")
    public String testToken(
            @RequestHeader("Authorization")
            String authHeader) {

        String token = authHeader.substring(7);

        return jwtService.extractUsername(token);
    }
}