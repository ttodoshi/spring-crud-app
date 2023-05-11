package com.example.authorization_service.service;

import com.example.authorization_service.config.JwtService;
import com.example.authorization_service.model.*;
import com.example.authorization_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getRole());
        String jwtToken = jwtService.generateToken(extraClaims, user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        User user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow();
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getRole());
        String jwtToken = jwtService.generateToken(extraClaims, user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
