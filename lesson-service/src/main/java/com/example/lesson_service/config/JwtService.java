package com.example.lesson_service.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    private final String SECRET_KEY;

    public JwtService(@Value("${secret_key}") String secretKey) {
        this.SECRET_KEY = secretKey;
    }

    public String extractUsername(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    public String extractRole(String jwtToken) {
        return (String) extractAllClaims(jwtToken).get("role");
    }

    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    public boolean isTokenValid(String jwtToken, UserDetails userDetails) {
        final String username = extractUsername(jwtToken);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(jwtToken);
    }

    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    private Date extractExpiration(String jwtToken) {
        return extractClaim(jwtToken, Claims::getExpiration);
    }

    private Claims extractAllClaims(String jwtToken) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(jwtToken)
                .getBody();
    }
}
