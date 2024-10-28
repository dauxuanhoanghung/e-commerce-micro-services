package com.ecommerce.jwt;

import java.util.Date;
import java.util.Map;

public interface JwtProviderInterface {

    String getUsernameFromToken(String token);

    Date extractExpiration(String token);

    Boolean isTokenExpired(String token);

    String generateToken(Map<String, Object> userDetails);

    Boolean validateToken(String token, Map<String, Object> userDetails);
}