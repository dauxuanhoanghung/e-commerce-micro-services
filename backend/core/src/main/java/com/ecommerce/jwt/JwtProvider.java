package com.ecommerce.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtProvider implements JwtProviderInterface {

    String secret;
    Long expirationMs;

    /**
     * JwtProvider Constructor for validating JWT
     *
     * @param secret the secret key for signing the JWT
     */
    public JwtProvider(String secret) {
        this.secret = secret;
        this.expirationMs = null;
    }

    /**
     * JwtProvider Constructor for generating JWT with expiration time
     *
     * @param secret       the secret key for signing the JWT
     * @param expirationMs the expiration time in milliseconds
     */
    public JwtProvider(String secret, Long expirationMs) {
        this.secret = secret;
        this.expirationMs = expirationMs;
    }

    @Override
    public String getUsernameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(this.getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    @Override
    public String generateToken(Map<String, Object> userDetails) {
        if (this.expirationMs == null) {
            throw new UnsupportedJwtException("Expiration time is not set");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.get("roles"));
        claims.put("username", userDetails.get("username"));
        return this.createToken(claims, userDetails.get("username").toString());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        if (this.expirationMs == null) {
            throw new IllegalStateException("Expiration time is not set");
        }

        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + this.expirationMs))
                .signWith(getSigningKey())
                .compact();
    }

    @Override
    public Boolean validateToken(String token, Map<String, Object> userDetails) {
        final String tokenUsername = this.getUsernameFromToken(token);
        final String username = userDetails.get("username").toString();
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }

    /**
     * Get the secret key for signing the JWT
     *
     * @return {@link SecretKey}
     */
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
}
