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

/**
 * JwtProvider class that implements {@link JwtProviderInterface}
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtProvider implements JwtProviderInterface {

    /**
     * The secret key for signing the JWT
     */
    String secret;

    /**
     * The expiration time in milliseconds
     */
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

    /**
     * The function to extract the claim from the token
     *
     * @param token The JWT token
     * @param claimsResolver The function to resolve the claim
     * @return {@link T}
     */
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

    /**
     * Generate the JWT token
     * @param userDetails The user details to be added to the token. Can be roles, username, etc.
     * @return {@link String} The JWT token
     */
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

    /**
     * Create the JWT token
     *
     * @param claims The claims to be added to the token. Can be roles, username, etc.
     * @param subject The subject of the token. Usually the username.
     * @return {@link String} The JWT token
     */
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

    /**
     * Validate the token with additional user details
     * <p>
     * The token is valid if the username in the token matches the username in the user details by checking with database.
     *
     * @param token - the JWT token
     * @param userDetails - the user details
     * @return {@link Boolean}
     */
    @Override
    public Boolean validateToken(String token, Map<String, Object> userDetails) {
        final String tokenUsername = this.getUsernameFromToken(token);
        final String username = userDetails.get("username").toString();
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }

    /**
     * Validate the token without any additional user details
     *
     * @param token the JWT token
     * @return {@link Boolean}
     */
    @Override
    public Boolean validateToken(String token) {
        return this.validateToken(token, new HashMap<>());
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
