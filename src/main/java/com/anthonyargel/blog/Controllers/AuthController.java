package com.anthonyargel.blog.Controllers;

import com.anthonyargel.blog.DTOs.AuthenticationRequest;
import com.anthonyargel.blog.DTOs.AuthenticationResponse;
import com.anthonyargel.blog.DTOs.RegisterRequest;
import com.anthonyargel.blog.Services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class AuthController {
    private final AuthenticationService authService;

    public AuthController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
     public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
    return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
