package org.example.offlinebackend.Util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // 32 bytes (256 bits) secret key
    private static final String SECRET = "OfflinePaymentSecureKeyForBankSync1234567890";

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 40)) // 5 minutes
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
