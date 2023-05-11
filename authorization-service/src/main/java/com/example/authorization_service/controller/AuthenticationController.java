package com.example.authorization_service.controller;

import com.example.authorization_service.model.AuthenticationRequest;
import com.example.authorization_service.model.AuthenticationResponse;
import com.example.authorization_service.model.RegisterRequest;
import com.example.authorization_service.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;
    @PostMapping("/register")
    @Operation(summary = "Register", tags = "Auth")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User registered",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    })
    })
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    @Operation(summary = "Authenticate", tags = "Auth")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User authenticated",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    })
    })
    public ResponseEntity<AuthenticationResponse> auth(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
